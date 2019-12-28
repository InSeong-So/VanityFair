package web.biz.vanityFair.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Product {

    // 채번
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long seqNo;

    // 상품코드
    @Id
    @Column(columnDefinition = "varchar(20)")
    private String productCd;

    // 상품번호
    @Column
    private long productNo;

    // 상품명
    @Column
    private String productNm;

    // 상품분류
    @Column
    private String productCategory;

    // 원산지
    @Column
    private String origin;

    // 제조사
    @Column
    private String maker;

    // 브랜드번호
    @Column
    private String brandNo;

    // 과세/비과세
    @Column
    private String tax;

    // 배송정책
    @Column
    private String deliveryType;

    // 적립금정책
    @Column
    private String useEmoney;

    // 유사검색어
    @Column
    private String keyword;

    // 가격대체문구
    @Column
    private String strPrice;

    // 짧은설명
    @Column
    private String shortDesc;

    // 상품설명
    @Column
    private String longDesc;

    // 메인이미지
    @Column
    private String mainImage;

    // 리스트이미지
    @Column
    private String listImage;

    // 상세이미지
    @Column
    private String detailImage;

    // 확대이미지
    @Column
    private String hoverImage;

    // 관리메모
    @Column
    private String memo;

    // 출시일
    @Column
    private String launchDaytime;

    // 상품표시여부
    @Column
    private String openYn;

    // 품절여부
    @Column
    private String outYn;

    // 재고량연동
    @Column
    private String useStock;

    // 옵션출력방식
    @Column
    private String optionType;

    // 가격/재고 옵션명
    @Column
    private String optionNm;

    // 가격/재고 옵션목록
    @Column
    private String optionList;

    // 추가상품제목
    @Column
    private String addOptionNm;

    // 추가상품목록
    @Column
    private String addOptionList;

    // 상품추가정보제목
    @Column
    private String exTitle;

    // 상품추가정보 1
    @Column
    private String ex1;

    // 상품추가정보 2
    @Column
    private String ex2;

    // 상품추가정보 3
    @Column
    private String ex3;

    // 상품추가정보 4
    @Column
    private String ex4;

    // 상품추가정보 5
    @Column
    private String ex5;

    // 상품추가정보 6
    @Column
    private String ex6;

    // 관련상품목록
    @Column
    private String relationList;

    // 관련상품번호
    @Column
    private String relationNo;

    // 타이틀태그
    @Column
    private String metaTitle;

    // 상품필수정보
    @Column
    private String extraInfo;

    // 모델명
    @Column
    private String modelNm;

    // 상품상태
    @Column
    private String productStatus;

    // 공급가격
    @Column
    private String providerPrice;

    // 입력옵션제목
    @Column
    private String inputAddOptionNm;

    // 입력옵션목록
    @Column
    private String inputAddOptionList;

    // 회원적립금혜택제외
    @Column
    private String excludeMemberReserve;

    // 회원할인혜택제외
    @Column
    private String excludeMemberDiscount;

    //제조일자
    @Column
    private String manufactureDate;

    // 시작유효일자
    @Column
    private String effectiveDateStart;

    // 종료유효일자
    @Column
    private String effectiveDateEnd;

    // 이벤트문구
    @Column
    private String eventStr;
}
