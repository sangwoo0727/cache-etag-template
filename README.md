# cache-etag-template

* 스프링 부트 프로젝트에 Etag를 이용한 Http 캐시 기능 적용 템플릿.

## 프로젝트 구조
* Book 리소스를 조회, 수정, 등록하는 api를 설계
* 조회 api에 대해서는 `/book/cache` 와 `/book/no-cache` 두가지 url을 만들어 각각 비교
* Etag 캐싱을 적용하기 위해 `ShallowEtagHeaderFilter` 사용

## 테스트 결과 비교

### Book 리소스 등록 후 첫 요청
* no-cache

    ![스크린샷 2021-07-22 오전 4 43 38](https://user-images.githubusercontent.com/46016511/126550171-96b6fcad-8b3e-4886-913a-6d3f356686a8.png)

* cache

    ![스크린샷 2021-07-22 오전 4 45 48](https://user-images.githubusercontent.com/46016511/126550448-bf288cfa-4d80-4b11-8a57-c5b618115cd3.png)

### 두번째 요청
* no-cache
  
  첫 요청과 다른 부분이 없다

* cache

    ![스크린샷 2021-07-22 오전 4 49 11](https://user-images.githubusercontent.com/46016511/126550862-ba8e0268-e47b-4c44-b8d1-e60de581e0bd.png)
    
### Book 리소스 변경 후 요청
* no-cache
  
   이전과 다른 부분이 없다.
   
* cache
    
    ![스크린샷 2021-07-22 오전 4 52 57](https://user-images.githubusercontent.com/46016511/126551324-2075ed8c-9962-4ca6-8412-879b5e935ef7.png)
