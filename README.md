# jwp-subway-path

# 기능 구현 목록

## step1

### 도메인

- 노선
    - [x] 노선은 이름, 색상 그리고 역(Station)이 있다.
        - [x] [제한 사항] 중복되는 이름이나 색상은 존재 할 수 없다.
        - [x] [제한 사항] 노선 내 역은 1개만 존재 할 수 없다.
    - [x] 역과 역 사이의 거리가 존재한다.
        - [x] [제한 사항] 거리는 양의 정수이어야 한다.
    - [x] 노선에 역을 추가할 수 있다.
        - [x] 최초 역 등록시, 두 역을 동시에 등록해야 한다.
        - 역은
            - [x] 맨 앞에 추가 될 수 있다.
            - [x] 역과 역 사이에 추가 될 수 있다.
                - [x] 역과 역 사이의 거리는 재배정 되어야 한다.
            - [x] 맨 뒤에 추가 될 수 있다.
    - [x] 노선에 역을 삭제할 수 있다.
        - [x] 역이 삭제될 때, 다시 이어지는 역의 거리는 합산된다.
        - [x] 노선 내 역이 2개 만 존재하는 경우, 하나의 역을 제거할 때 두 역이 모두 제거 된다.

- (노선 위의) 역
    - [x] 역 이름이 존재한다.
    - [x] 인접 역과의 거리가 존재한다.

### 웹 API

- [x] 노선에 역 등록 API
- [x] 노선에 역 제거 API
- [x] 노선 조회 API
- [x] 노선 목록 API

### 개선 사항

- [x] 도메인 로직 내부에서 처리하기
    - [x] 역 추가 기능 통합
    - [x] 역 제거 기능 통합
- [x] 예외 처리 공통 관심사 분리
- [x] Section의 FK를 Station의 PK로 수정
- [x] API 응답 개선
- [x] 예외 케이스 분리/구분

## step2

- [x] 프로덕션과 테스트의 DB 설정 분리

### 도메인

- 최단 거리 경로
    - [x] 환승을 고려하여 최단 거리 경로를 계산한다.
    - [x] 경로내의 모든 역을 포함 한다.

- 요금
    - [x] 거리에 비례하여 요금이 결정된다.

### 웹 API

- [x] 경로 조회 API

## step3

### 도메인

- 노선
    - [x] 노선 추가 요금 반영

- 요금 정책 수정
    - [ ] 경로에 따른 노선 요금 반영
        - 이동 경로내 노선들 중 추가요금이 가장 높은 금액 추가 요금 부과
    - [ ] 연령별 할인
        - [ ] 청소년(13 ~ 18) : 운임료에서 350을 제외한 뒤, 20% 할인
        - [ ] 어린이(6 ~ 12) : 운임료에서 350을 제외한 뒤, 50% 할인

### 웹 API

- 경로 조회 API 수정
    - [ ] 할인 정책 반영을 위한 연령 정보 요청 추가
