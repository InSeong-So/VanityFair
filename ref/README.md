# Thymeleaf 템플릿 엔진
- html 태그에서 script 코드 사용하기
    ```html
    <script th:inline="javascript">
    /*<![CDATA[*/

    var message = [[${message}]];
    alert(message);

    /*]]>*/
    </script>
    ```

- 조건식
    ```html
    <!-- switch-case 문-->
    <div th:switch="${user.role}">
        <p th:case="'admin'">User is an administrator</p>
        <p th:case="#{roles.manager}">User is a manager</p>
        <p th:case="*">User is some other thing</p>
    </div>

    <!-- if 문-->
    <div th:with="condition=${potentially_complex_expression}" th:remove="tag">
        <h2 th:if="${condition}">Hello!</h2>
        <span th:unless="${condition}" class="xxx">Something else</span>
    </div>
    ```

# ORM 프레임워크 - JPA
- [연관관계매핑](https://victorydntmd.tistory.com/208)

# 자바 메일
- [자바 메일 발송 참조]](https://offbyone.tistory.com/167)