package com.codegym.controller;


import com.codegym.dto.IClothesCartDto;
import com.codegym.dto.IProductDto;
import com.codegym.dto.IProductSizeDto;
import com.codegym.dto.Quantity;
import com.codegym.jwt.JwtTokenUtil;
import com.codegym.model.Customer;
import com.codegym.model.OrderDetail;
import com.codegym.model.ProductSize;
import com.codegym.model.User;
import com.codegym.payload.request.LoginRequest;
import com.codegym.payload.request.LoginResponse;
import com.codegym.service.ICustomerService;
import com.codegym.service.IOrderDetailService;
import com.codegym.service.IProductService;
import com.codegym.service.IProductSizeService;
import com.codegym.service.security.impl.MyUserDetails;
import com.codegym.service.security.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clothes")
@CrossOrigin("*")
public class ClothesController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IOrderDetailService iOrderDetailService;

    @Autowired
    private IProductSizeService iProductSizeService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername());
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(jwt, myUserDetails.getUsername(), roles));
    }

    @GetMapping("/findUsername")
    public ResponseEntity<?> showUsername(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromJwtToken(headerAuth.substring(7));
        Optional<User> user = userService.showUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/list-newest/{nameProduct}&{manufacturerProduct}&{typeProduct}&{priceStart}&{priceEnd}")
    public ResponseEntity<Page<IProductDto>> showListClothesNewest(@PageableDefault(value = 6) Pageable pageable,
                                                                   @PathVariable("nameProduct") String nameProduct,
                                                                   @PathVariable("manufacturerProduct") String manufacturerProduct,
                                                                   @PathVariable("typeProduct") String typeProduct,
                                                                   @PathVariable("priceStart") Integer priceStart,
                                                                   @PathVariable("priceEnd") Integer priceEnd) {
        Page<IProductDto> clothesList = iProductService.showListClothes(nameProduct, manufacturerProduct, typeProduct, priceStart, priceEnd, pageable);


        return new ResponseEntity<>(clothesList, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<Page<IProductDto>> showList(@RequestParam(value = "nameProduct", defaultValue = "") String nameProduct,
                                                      @PageableDefault(value = 16, sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IProductDto> clothesList = iProductService.showList(nameProduct, pageable);
        if (clothesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clothesList, HttpStatus.OK);

    }


    @GetMapping("/list-price-asc")
    public ResponseEntity<Page<IProductDto>> showListPriceAsc(@RequestParam(value = "nameProduct", defaultValue = "") String nameProduct,
                                                              @PageableDefault(value = 16, sort = "price", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<IProductDto> clothesList = iProductService.showList(nameProduct, pageable);
        if (clothesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clothesList, HttpStatus.OK);

    }

    @GetMapping("/list-newest")
    public ResponseEntity<Page<IProductDto>> showListClothesNewest(@RequestParam(value = "nameProduct", defaultValue = "") String nameProduct,
                                                                   @PageableDefault(value = 16, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IProductDto> clothesList = iProductService.showList(nameProduct, pageable);
        if (clothesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clothesList, HttpStatus.OK);

    }


    @GetMapping("/get-customer/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username) {
        Optional<Customer> customer = iCustomerService.findCustomerByUsername(username);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<IClothesCartDto>> showCartByUser(@PathVariable("id") Integer id) {
        List<IClothesCartDto> cart = iOrderDetailService.findCartByUser(id);

        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<IProductDto> getClothesDetail(@PathVariable("id") Integer id) {
        IProductDto clothes = iProductService.findById(id);
        if (clothes == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(clothes, HttpStatus.OK);
    }

    @GetMapping("/add-cart/{quantity}&{customerId}&{productSizeId}")
    public ResponseEntity<OrderDetail> addToCart(@PathVariable("quantity") Integer quantity,
                                                 @PathVariable("customerId") Integer customerId,
                                                 @PathVariable("productSizeId") Integer productSizeId) {
        iOrderDetailService.addProductCart(quantity, customerId, productSizeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clothes-size/{id}")
    public ResponseEntity<List<IProductSizeDto>> showListClothesSize(@PathVariable("id") Integer id) {
        List<IProductSizeDto> clothesSizeList = iProductSizeService.findAllSizeByClothes(id);
        if (clothesSizeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clothesSizeList, HttpStatus.OK);
    }


    @GetMapping("/desc-quantity/{id}")
    public ResponseEntity<OrderDetail> descQuantityCart(@PathVariable("id") Integer id) {
        iOrderDetailService.descQuantity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/asc-quantity/{id}")
    public ResponseEntity<OrderDetail> ascQuantityCart(@PathVariable("id") Integer id) {
        iOrderDetailService.ascQuantity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/remove-cart/{id}")
    public ResponseEntity<OrderDetail> removeCart(@PathVariable("id") Integer id) {
        iOrderDetailService.removeCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/update-quantity-product")
    public ResponseEntity<?> updateQuantityProduct(@RequestParam("customerId") Integer customerId) {
        List<Quantity> productSizeList = iProductSizeService.findAllProductSizeList(customerId);

        for (Quantity quantity : productSizeList) {
            iProductSizeService.updateQuantity(quantity.getQuantityProduct() - quantity.getQuantity(), quantity.getProductId());
        }
        iOrderDetailService.paymentClothes(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/list-product-size)")
//    public ResponseEntity<List<ProductSize>> findAllProductSize() {
//        List<ProductSize> productSizeList = iProductSizeService.findAllProductSizeList();
//        if (productSizeList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(productSizeList, HttpStatus.OK);
//    }



    @GetMapping("/history-cart/{customerId}")
    public ResponseEntity<Page<IClothesCartDto>> findAllHistoryCart(@PathVariable("customerId") Integer customerId,
                                                                   @PageableDefault(value = 3) Pageable pageable) {
        Page<IClothesCartDto> iClothesCartDtoPage = iOrderDetailService.findAllHistoryCart(customerId, pageable);
        if (iClothesCartDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iClothesCartDtoPage, HttpStatus.OK);
    }



}
