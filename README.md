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


[Uploading {
	"info": {
		"_postman_id": "679e7923-f4eb-42b3-955e-41a9bbcbb6a3",
		"name": "멋마마켓-유강민",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "27334561",
		"_collection_link": "https://www.postman.com/lunar-crescent-227721/workspace/mutsamarket2/collection/27334561-679e7923-f4eb-42b3-955e-41a9bbcbb6a3?action=share&creator=27334561&source=collection_link"
	},
	"item": [
		{
			"name": "로그인",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"email\" : \"example@example.com\",\r\n    \"password\" : \"password\"\r\n}\r\n",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/auth/login",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"auth",
						"login"
					]
				}
			},
			"response": []
		},
		{
			"name": "회원가입",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"email\": \"example@example.com\",\r\n    \"password\": \"password\",\r\n    \"passwordConfirm\": \"password\",\r\n    \"phoneNumber\": \"123-456-7890\",\r\n    \"address\": \"Sample Address\",\r\n    \"roles\": [\"USER\"]\r\n}\r\n",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/auth/register",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"auth",
						"register"
					]
				}
			},
			"response": []
		},
		{
			"name": "상품등록",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Authorization",
						"value": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwiaWF0IjoxNjkwOTYxMzk2LCJleHAiOjE2OTExMjYzOTYsImF1dGgiOiJST0xFX1VTRVIifQ.BBUOaArSAfsooheLX1pV682d9SSazKmg-CEav6VChgTHoOCo4ah3DvIHhzjXcEEgIV7A4JiEM4LxHgNfSIuN_g",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"title\": \"Sample Title\",\r\n  \"description\": \"Sample Description\",\r\n  \"minPriceWanted\": 100,\r\n  \"status\": \"ON_SALE\",\r\n  \"writer\": \"John Doe\",\r\n  \"password\": \"password123\"\r\n}\r\n",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/items",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"items"
					]
				}
			},
			"response": []
		},
		{
			"name": "상품조회",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Authorization",
						"value": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwiaWF0IjoxNjkwOTYxMzk2LCJleHAiOjE2OTExMjYzOTYsImF1dGgiOiJST0xFX1VTRVIifQ.BBUOaArSAfsooheLX1pV682d9SSazKmg-CEav6VChgTHoOCo4ah3DvIHhzjXcEEgIV7A4JiEM4LxHgNfSIuN_g",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8080/items?page=1&limit=1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"items"
					],
					"query": [
						{
							"key": "page",
							"value": "1"
						},
						{
							"key": "limit",
							"value": "1"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "협상 제안",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Authorization",
						"value": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwiaWF0IjoxNjkwOTYxMzk2LCJleHAiOjE2OTExMjYzOTYsImF1dGgiOiJST0xFX1VTRVIifQ.BBUOaArSAfsooheLX1pV682d9SSazKmg-CEav6VChgTHoOCo4ah3DvIHhzjXcEEgIV7A4JiEM4LxHgNfSIuN_g",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"writer\" : \"example@example.com\",\r\n    \"password\" : \"password\",\r\n    \"suggestedPrice\" : 100\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/items/1/proposals",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"items",
						"1",
						"proposals"
					]
				}
			},
			"response": []
		},
		{
			"name": "내 협상 보기",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Authorization",
						"value": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwiaWF0IjoxNjkwOTYxMzk2LCJleHAiOjE2OTExMjYzOTYsImF1dGgiOiJST0xFX1VTRVIifQ.BBUOaArSAfsooheLX1pV682d9SSazKmg-CEav6VChgTHoOCo4ah3DvIHhzjXcEEgIV7A4JiEM4LxHgNfSIuN_g",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8080/items/1/proposals?page=1&writer=John Doe&password=password123",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"items",
						"1",
						"proposals"
					],
					"query": [
						{
							"key": "page",
							"value": "1"
						},
						{
							"key": "writer",
							"value": "John Doe"
						},
						{
							"key": "password",
							"value": "password123"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "내 댓글 보기",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Authorization",
						"value": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwiaWF0IjoxNjkwOTYxMzk2LCJleHAiOjE2OTExMjYzOTYsImF1dGgiOiJST0xFX1VTRVIifQ.BBUOaArSAfsooheLX1pV682d9SSazKmg-CEav6VChgTHoOCo4ah3DvIHhzjXcEEgIV7A4JiEM4LxHgNfSIuN_g",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8080/items/1/comments",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"items",
						"1",
						"comments"
					]
				}
			},
			"response": []
		},
		{
			"name": "댓글등록",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Authorization",
						"value": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwiaWF0IjoxNjkwOTYxMzk2LCJleHAiOjE2OTExMjYzOTYsImF1dGgiOiJST0xFX1VTRVIifQ.BBUOaArSAfsooheLX1pV682d9SSazKmg-CEav6VChgTHoOCo4ah3DvIHhzjXcEEgIV7A4JiEM4LxHgNfSIuN_g",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"salesItemId\": 1,\r\n  \"writer\": \"JohnDoe\",\r\n  \"password\": \"secretpassword\",\r\n  \"content\": \"This is the content of the sales item\",\r\n  \"reply\": \"This is the reply to the sales item\"\r\n}\r\n",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/items/1/comments",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"items",
						"1",
						"comments"
					]
				}
			},
			"response": []
		}
	]
}멋마마켓-유강민.postman_collection.json…]()
