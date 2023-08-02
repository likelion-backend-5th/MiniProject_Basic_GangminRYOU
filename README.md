# **백엔드 스쿨 5기 미니 개인프로젝트 - ♻️멋사마켓♻️**

```
사람들이 많이 사용하고 있는 🥕당근마켓, 중고나라 등의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.

사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여
수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드를 만들어봅시다.
```

<br>

## History

### 2023-06-29
> Create git repsitory - 'MiniProject_Basic_GangminRYOU'

<!-- ![ex_img](img/*.png) -->

# Dependencies

- Spring Data Jpa
- Spring Web
- Spring Security Crypto
- Spring Validation
- Lombok
- DevTools
- MapStruct
- H2
- MockMvc
- Mockito

# API 목록

## Item 관련 API

```http request
### 제품 등록

POST http://localhost:8080/items
Content-Type: application/json

{
  "title": "중고 맥북 팝니다",
  "description": "2019년 맥북 프로 13인치 모델입니다",
  "minPriceWanted": 1000000,
  "writer": "jeeho.dev",
  "password": "1qaz2wsx"
}

### 제품 페이지 조회

GET localhost:8080/items?page=1&limit=2

### 단건 조회

GET localhost:8080/items/1


### 제품 수정

PUT localhost:8080/items/1
Content-Type: application/json

{
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1250000,
    "writer": "jeeho.dev",
    "password": "1qaz2wsx"
}

### 이미지 등록

PUT localhost:8080/items/1/image
Content-Type: application/x-www-form-urlencoded

### 상품 삭제

DELETE localhost:8080/items/1
Content-Type: application/json

{
  "writer": "jeeho.dev",
  "password": "1qaz2wsx"
}
```

## 댓글 관련 API


```http request
### 댓글 등록

POST localhost:8080/items/1/comments
Content-Type: application/json

{
  "writer": "jeeho.edu",
  "password": "qwerty1234",
  "content": "할인 가능하신가요?"
}


### 댓글 조회

GET localhost:8080/items/1/comments

### 댓글 수정

PUT localhost:8080/items/1/comments/1
Content-Type: application/json

{
  "writer": "jeeho.edu",
  "password": "qwerty1234",
  "content": "할인 가능하신가요? 1000000 정도면 고려 가능합니다"
}

### 답글 입력

PUT localhost:8080/items/1/comments/1/reply
Content-Type: application/json

{
  "writer": "jeeho.dev",
  "password": "1qaz2wsx",
  "reply": "안됩니다"
}

### 댓글삭제

DELETE localhost:8080/items/1/comments/1
Content-Type: application/json

{
  "writer": "jeeho.edu",
  "password": "qwerty1234"
}
```

## 제안 API 목록

```http request
### 제안 생성

POST localhost:8080/items/1/proposals
Content-Type: application/json

{
  "writer": "gangmin",
  "password": "1234",
  "suggestedPrice": 1000000
}

### 제안 조회

GET localhost:8080/items/1/proposals?writer=jeeho.dev&password=1qaz2wsx&page=1


### 제안 수정

PUT localhost:8080/items/1/proposals/1
Content-Type: application/json

{
  "writer": "gangmin",
  "password": "1234",
  "suggestedPrice": 1100000
}

### 제안 삭제

DELETE localhost:8080/items/1/proposals/1
Content-Type: application/json

{
"writer": "gangmin",
"password": "1234"
}

### 제안 수락

PUT localhost:8080/items/1/proposals/1/accept
Content-Type: application/json

{
  "writer": "jeeho.dev",
  "password": "1qaz2wsx",
  "status": "ACCEPTED"
}

### 제안 확정

PUT localhost:8080/items/1/proposals/1/confirm
Content-Type: application/json

{
  "writer": "gangmin",
  "password": "1234",
  "status": "CONFIRMED"
}
```

### 테스트 방법

1. 프로젝트를 clone받습니다.
2. Application을 실행 후, 프로젝트 내부의 http디렉토리로 들어 갑니다.
3. item부터 댓글 제안 순으로 http 명령을 실행합니다.

# 기술 도입 이유

- MapStruct
  - 도메인 계층의 웹 의존성 분리를 위해, 컨트롤러에게 DTO 변환역할을 위임하였습니다. 
  - 그러다 보니 컨트롤러 로직이 더러워져, Mapper를 사용해 로직을 더 가독성있게 만들었고, 컨트롤러의 엔티티의존성을 없앴습니다.

# API 명세

## 로그인 API (`로그인`)

- Method: POST
- URL: http://localhost:8080/auth/login
- Request Body:
```json
  {
    "email": "example@example.com",
    "password": "password"
  }

    Response: 없음 (Response가 비어있습니다.)
```
회원가입 API (회원가입)
```
    Method: POST
    URL: http://localhost:8080/auth/register
    Request Body:
```
```json

    {
      "email": "example@example.com",
      "password": "password",
      "passwordConfirm": "password",
      "phoneNumber": "123-456-7890",
      "address": "Sample Address",
      "roles": ["USER"]
    }
```
Response: 없음 (Response가 비어있습니다.)

상품등록 API (상품등록)

    Method: POST
    URL: http://localhost:8080/items
    Request Body:

  ```json

{
  "title": "Sample Title",
  "description": "Sample Description",
  "minPriceWanted": 100,
  "status": "ON_SALE",
  "writer": "John Doe",
  "password": "password123"
}

Request Header:

makefile

    Key: Authorization
    Value: Bearer [Access Token]

    Response: 없음 (Response가 비어있습니다.)
```
상품조회 API (상품조회)
```
    Method: GET
    URL: http://localhost:8080/items?page=1&limit=1
    Request Header:
    makefile
  ```
    Key: Authorization
    Value: Bearer [Access Token]

    Response: 없음 (Response가 비어있습니다.)
    ```
협상 제안 API (협상 제안)

    Method: POST
    URL: http://localhost:8080/items/1/proposals
    Request Body:

    json

{
  "writer": "example@example.com",
  "password": "password",
  "suggestedPrice": 100
}

Request Header:

makefile

    Key: Authorization
    Value: Bearer [Access Token]

    Response: 없음 (Response가 비어있습니다.)

내 협상 보기 API (내 협상 보기)

    Method: GET
    URL: http://localhost:8080/items/1/proposals?page=1&writer=John Doe&password=password123
    Request Header:

    makefile

    Key: Authorization
    Value: Bearer [Access Token]

    Response: 없음 (Response가 비어있습니다.)

내 댓글 보기 API (내 댓글 보기)

    Method: GET
    URL: http://localhost:8080/items/1/comments
    Request Header:

    makefile

    Key: Authorization
    Value: Bearer [Access Token]

    Response: 없음 (Response가 비어있습니다.)

댓글 등록 API (댓글 등록)

    Method: POST
    URL: http://localhost:8080/items/1/comments
    Request Body:

    json

{
  "salesItemId": 1,
  "writer": "JohnDoe",
  "password": "secretpassword",
  "content": "This is the content of the sales item",
  "reply": "This is the reply to the sales item"
}

Request Header:

makefile

Key: Authorization
Value: Bearer [Access Token]

Response: 없음 (Response가 비어있습니다.)
