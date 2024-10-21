package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Router;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductSaveService;
import org.koreait.product.templates.ProductForm;

import java.util.Scanner;

/**
 * 상품 등록/수정 컨트롤러
 *
 */
public class ProductController extends Controller {

    public ProductController() {
       setPromptProcess(() -> {
           Utils.drawLine('-', 30);

           Scanner sc = Router.sc;
           Product item = new Product();

           // 상품명
           String name = Utils.getString("상품명", "상품명을 입력하세요.");
           item.setName(name);

           // 판매가
           int price = Utils.getNumber("판매가", "판매가를 입력하세요.");
           item.setPrice(price);

            // 재고
           int stock = Utils.getNumber("재고", "재고를 입력하세요.");
           item.setStock(stock);

           // 상품 정보 저장 처리
           ProductSaveService saveService = BeanContainer.getBean(ProductSaveService.class);
           saveService.save(item);

           System.out.println("상품이 저장되었습니다.");
           // 저장 이후에 상품 목록으로 페이지 이동
           
           Utils.loadController(ProductListController.class);
       });
    }

    @Override
    protected String getPromptText() {
        return "등록할 상품 정보를 입력하세요(메인 메뉴: M, 종료: Q).\n";
    }

    @Override
    public void view()  {
        Utils.loadTpl(ProductForm.class);
    }
}
