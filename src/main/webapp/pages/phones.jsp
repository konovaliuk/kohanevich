<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="myTag" uri="http://www.kohanevich.com/userframe"%>
<html>
<head>
    <title>Phones</title>
    <style>
        .list{
            float: left;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${not empty user && !user.admin}">
        <div class="list">
            <c:forEach var="product" items="${phones}">
              <c:out value="${product.model}"/>
                <c:out value="${product.price}"/> <br/>
                <c:if test="${product.stock > 0}">
                <form method="post" action="/?command=bucket">
                    <input type="hidden" name="addToBucket" value="${product.series}">
                    <input type="submit" value="buy">
                    <input name="quantity" min="1" max ="${product.stock}" value="1" type="number"/>
                </form>
                    <br/>
                </c:if>
           </c:forEach>
        </div>
        <myTag:UserFrameTag/>
    </c:when>
    <c:when test="${not empty user && user.admin}">
        <div class="list">
            <c:forEach var="product" items="${phones}">
                <c:out value="${product.model}"/> <br/>
                <form method="post" action="/?command=delete">
                    <input type="hidden" name="delete" value="${product.series}">
                    <input type="submit" value="Delete">
                </form>
            </c:forEach>
        </div>
        <form align = "right" method="post" action="/">
            <h1>Add new product</h1>
            <p> Company <input type="text" name="company" required></p>
            <p> Model <input type="text" name="model" required></p>
            <p> Series <input type="text" name="series" required></p>
            <p> Price <input type="number" min="0" name="price" required></p>
            <p> Stock <input type="number" min="0" name="stock" required></p>
            <input type="hidden" name="productType" value="Mobile">
            <p><input type="submit" value="Send"></p>
            <input type="hidden" name="command" value="addProduct">
        </form>
    </c:when>
    <c:otherwise>
            <c:forEach var="product" items="${phones}">
                <c:out value="${product.model}"/> <br/>
            </c:forEach>
    </c:otherwise>
</c:choose>
<p align="center">
    <a href="/?command=phones&page=1">1</a>
    <a href="/?command=phones&page=2">2</a>
    <a href="/?command=phones&page=3">3</a>
    <a href="/?command=phones&page=4">4</a>
    <a href="/?command=phones&page=5">5</a>
    <a href="/"><button>Home</button></a>
</p>
</body>
</html>
