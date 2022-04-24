# OTLanguage <a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FPersesTitan%2FOTLanguage&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false"/></a>

직접 자신만의 언어를 1개 만들어 보고 싶었는데 만드는 법을 몰라서 일단 제가 생각해본 만들어 보았습니다. 이게 맞는지는 잘모르겠지만요;;</br>
언어의 의미는 OTL (절망) 하는 아이콘을 모티브로 한 것이지만 다양한 이모티콘을 섞었습니다.</br>

## 사용방법

</br>EXEFile로 가신뒤 다운받을 버전을 들어간뒤 exe파일을 다운받으면 됩니다.</br>

실행 방법은 2가지가 있습니다.</br>
  * 첫번째 방법으로는 실행시킬 .otl(또는 .OTL)파일을 OTLanguage.exe파일과 같은 경로에 두고 실행 파일을 실행시킨뒤 파일이름을 입력하시면 됩니다.
</br>(ex. 입력: start.otl)</br>
  * 두번째 방법으로는 실행시킬 .otl파일을 다음으로 열기 선택 후 실행 시킬 프로그램를 OTLanuage.exe파일로 선택하시면 결과값이 출력됩니다.


## 출력

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


### 변수 선언
</br> 변수는 [문자1글자] + [변수타입] + [문자1글자]로 선언하시면 됩니다.</br>


**단 버전 0.0.1에서는 변수 타입 구분이 존재하지 않습니다.**       
>ㅇㅈㅇ (정수) : int형 </br>
>ㅇㅉㅇ (정수) : long형 </br>
>ㅇㅂㅇ (블린) : boolean형 </br>
>ㅇㅁㅇ (문자) : String형 </br>
>ㅇㄱㅇ (글자) : char형 </br>
>ㅇㅅㅇ (실수) : float형 </br>
>ㅇㅆㅇ (실수) : double형 </br>
> ###### ㅇ 대신 다른 문자를 넣어서 변수이름 선언 가능함!!

---

### 변수명 선언
</br> 변수를 생성하고 싶으면 [문자1글자] + [변수타입] + [문자1글자]로 이루어져 있습니다. 최종적으로 중앙에 변수타입에 맞아야하며 반드시 3글자가 되어야합니다.

```
예시) 
ㅇㅈㅇ -> 자료형이 int인 ㅇㅈㅇ이라는 변수 생성
```

---

### 변수에 값 넣기
</br> 변수에 값을 넣을려면 [변수이름]+[;]+[ 넣을 값] 을 넣으시면 됩니다.</br>

```
에시)
ㅇㅅㅇ; 100   -> ㅇㅅㅇ에 100이라는 값을 넣음
```

---

### 변수 값 가져오기
</br> 변수값을 가져올려면 [:]+[변수이름]으로 선언하시면 됩니다.</br>

```
예시)
ㄱㅈㄱ; 100     -> ㄱㅈㄱ에 100을 넣음
운 :ㄱㅈㄱ       -> 100이 출력됨
운 ㄱㅈㄱ        -> ㄱㅈㄱ이 출력됨
```
