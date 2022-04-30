# OTLanguage <a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FPersesTitan%2FOTLanguage&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false"/></a>

직접 자신만의 언어를 1개 만들어 보고 싶었는데 만드는 법을 몰라서 일단 제가 생각해본 만들어 보았습니다. 이게 맞는지는 잘모르겠지만요;;</br>
언어의 의미는 OTL (절망) 하는 아이콘을 모티브로 한 것이지만 다양한 이모티콘을 섞었습니다.</br>
</br>이 언어는 띄어쓰기와 줄바꿈이 중요합니다. 사용하실때 주의해서 사용해주세요.

## 사용방법 
###### 추가됨 - V0

[모든 버전 다운로드](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/PersesTitan/OTLanguage/tree/master/EXEFile)</br>
[V0 버전 다운로드](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/PersesTitan/OTLanguage/tree/master/EXEFile/V0)</br>
[V1 버전 다운로드](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/PersesTitan/OTLanguage/tree/master/EXEFile/V1)</br>
[V2 버전 다운로드](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/PersesTitan/OTLanguage/tree/master/EXEFile/V2)</br>

</br>EXEFile로 가신뒤 다운받을 버전을 들어간뒤 exe파일을 다운받으면 됩니다.</br>

실행 방법은 2가지가 있습니다.</br>
  * 첫번째 방법으로는 실행시킬 .otl(또는 .OTL)파일을 OTLanguage.exe파일과 같은 경로에 두고 실행 파일을 실행시킨뒤 파일이름을 입력하시면 됩니다.
</br>(ex. 입력: start.otl)</br>
  * 두번째 방법으로는 실행시킬 .otl파일을 다음으로 열기 선택 후 실행 시킬 프로그램를 OTLanuage.exe파일로 선택하시면 결과값이 출력됩니다.


## 출력
###### 추가됨 - V0

</br> 출력을 하고 싶으면 맨앞에다가 ㅇㅜㄴ(한글), 운, otl(대소문자 구분하지 않음)를 입력하고 뒤에 출력할 문자를 넣으면 출력됩니다.
```
예시)
운 Hello World!    -> Hello World!
ㅇㅜㄴ Hello World! -> Hello World!
otl Hello World!  -> Hello World!
OTL Hello World!  -> Hello World!
oTL Hello World!  -> Hello World!
```

## 변수
###### 추가됨 - V0

### 변수 선언
###### 추가됨 - V0, V1
</br> 변수는 [문자1글자] + [변수타입] + [문자1글자]로 선언하시면 됩니다.</br>


**단, 버전 V0에서는 변수 타입 구분이 존재하지 않습니다.** </br>
**V2 에서 부턴 타입이 일치하지 않으면 경고창이 발생합니다. 바로 문제가 생기진 않지만 추후 연산 등의 문제가 발생할 수 있습니다.**
###### 'ㅇ' 대신 다른 문자를 넣어서 변수이름 선언 가능함!!
```
ㅇㅈㅇ (정수) : int형 </br>
ㅇㅉㅇ (정수) : long형 </br>
ㅇㅂㅇ (블린) : boolean형 </br>
ㅇㅁㅇ (문자) : String형 </br>
ㅇㄱㅇ (글자) : char형 </br>
ㅇㅅㅇ (실수) : float형 </br>
ㅇㅆㅇ (실수) : double형 </br>
```
---

### 변수명 선언
###### 추가됨 - V0, V1
</br> 변수를 생성하고 싶으면 [문자1글자] + [변수타입] + [문자1글자]로 이루어져 있습니다. 최종적으로 중앙에 변수타입에 맞아야하며 반드시 3글자가 되어야합니다.

```
예시) 
ㅇㅈㅇ -> 자료형이 int인 ㅇㅈㅇ이라는 변수 생성
```

---

### 변수에 값 넣기
###### 추가됨 - V0, V1
</br> 변수에 값을 넣을려면 [변수이름]+[;]+[ 넣을 값] 을 넣으시면 됩니다.</br>

```
예시)
ㅇㅅㅇ; 100   -> ㅇㅅㅇ에 100이라는 값을 넣음
```

---

### 변수 값 가져오기
###### 추가됨 - V0, V1
</br> 변수값을 가져올려면 [:]+[변수이름]으로 선언하시면 됩니다.</br>

```
예시)
ㄱㅈㄱ; 100     -> ㄱㅈㄱ에 100을 넣음
운 :ㄱㅈㄱ       -> 100이 출력됨
운 ㄱㅈㄱ        -> ㄱㅈㄱ이 출력됨
```

## ^^문 (for문)
###### 추가됨 - (V2), V3

### 사용법
</br> ^^문은 다른언어에서 for문이랑 같은 역할을 합니다.
</br> 시작은 [태그] + [시작 숫자] + ^ + [중간 숫자] + ^ + [마지막 숫자]
</br> 마지막은 [태그] + ^^
</br> 태그는 예약어를 제외한 글자입니다. 처음과 끝을 구분할 수 있도록 태그는 짝으로 맞춰야합니다.
</br>
</br> 자바 : for (int i = 시작 숫자; i<중간숫자; i=+마지막 숫자)
</br>
</br> V3버전 기준으로 2중 ㅇㅍㅇ문는 작동 되지 않습니다.
</br> ㅇㅍㅇ 문 (V1)버전 => (V2)버전 ^^문이 되었습니다.

```
예시)
마 0^5^1
운 Hello
마 ^^

출력)
Hello
Hello
Hello
Hello
Hello
```

# 개발 기록

* V0 - 출력문, 변수 추가 </br>
* V1 - ㅇㅍㅇ문 추가, 변수 개선 </br>
* V2 - ㅇㅍㅇ문 => ^^문으로 바뀜, 소스 개선
