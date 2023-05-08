import {Component, OnInit} from '@angular/core';
import {IClothesDto} from '../../model/i-clothes-dto';
import {IClothesSizeDto} from '../../model/i-clothes-size-dto';
import {compareNumbers} from '@angular/compiler-cli/src/diagnostics/typescript_version';
import {ClothesService} from '../../service/clothes.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';
import {Title} from '@angular/platform-browser';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clothes-detail',
  templateUrl: './clothes-detail.component.html',
  styleUrls: ['./clothes-detail.component.css']
})
export class ClothesDetailComponent implements OnInit {
  id: number;
  clothes: IClothesDto;
  quantityChoose = 1;
  clothesSizeList: IClothesSizeDto[];
  clothesSizeIdChoose = 0;
  idUser: number;
  quantitySellByClothes = 0;
  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;


  constructor(private clothesService: ClothesService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenStorageService,
              private router: Router,
              private title: Title) {
  }

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.snapshot.params.id);
    this.getClothesById();
    this.username = '';
    this.showUsername();
    this.getAllSizeByClothes();
  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;

    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;

    if (this.username !== '' && this.username !== undefined) {
      this.clothesService.findCustomer(this.username).subscribe(customer => {
          if (customer != null) {
            this.idUser = customer.id;
            console.log(this.idUser);
          }
        },
        error => {
          console.log(error);
        });
    }
  }

  getClothesById(): void {
    this.clothesService.findClothesById(this.id).subscribe(value => {
      this.clothes = value;
    }, error => {
      console.log(error);
    });
  }

  getAllSizeByClothes(): void {
    this.clothesService.findAllSizeByClothes(this.id).subscribe(value => {
        this.clothesSizeList = value;
      }, error => {
        console.log(error);
      }
    );
  }

  descQuantity(): void {
    this.quantityChoose--;
  }

  ascQuantity(): void {
    this.quantityChoose++;
  }


  chooseClothesSize(id: number) {
    this.quantityChoose = 1;
    this.clothesSizeIdChoose = id;
  }

  addToCart(): void {
    this.clothesService.addToCart(this.quantityChoose, this.idUser, this.clothesSizeIdChoose).subscribe(() => {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer);
          toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
      });

      Toast.fire({
        icon: 'success',
        title: 'Thêm vào giỏ hàng thành công!'
      }).then(r => location.replace('cart'));
    }, error => {
      console.log(error);
    });


  }
}
