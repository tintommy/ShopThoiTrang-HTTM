<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
 <link href="assets/css/font-awesome.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">   
    <!-- SmartMenus jQuery Bootstrap Addon CSS -->
    <link href="assets/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
    <!-- Product view slider -->
    <link rel="stylesheet" type="text/css" href="assets/css/jquery.simpleLens.css">    
    <!-- slick slider -->
    <link rel="stylesheet" type="text/css" href="assets/css/slick.css">
    <!-- price picker slider -->
    <link rel="stylesheet" type="text/css" href="assets/css/nouislider.css">
    <!-- Theme color -->
    <link id="switcher" href="assets/css/theme-color/default-theme.css" rel="stylesheet">
    <!-- <link id="switcher" href="css/theme-color/bridge-theme.css" rel="stylesheet"> -->
    <!-- Top Slider CSS -->
    <link href="assets/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">

    <!-- Main style sheet -->
    <link href="assets/css/style.css" rel="stylesheet">    

    <!-- Google Font -->
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> 

  </head>
  <!-- !Important notice -->
  <!-- Only for product page body tag have to added .productPage class -->
  
  <title>Image Search</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            text-align: center;
        }

        .title {
            font-size: 24px;
            font-weight: bold;
            margin: 20px 0;
        }

        .upload-btn-wrapper {
            position: relative;
            overflow: hidden;
            display: inline-block;
        }

        .btn {
            border: 2px solid gray;
            color: gray;
            background-color: white;
            padding: 8px 20px;
            border-radius: 8px;
            font-size: 20px;
        }

        .upload-btn-wrapper input[type=file] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }

        .uploaded-image {
            max-width: 400px;
            margin: 20px auto;
        }

        .recommendations {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .recommendation-item {
            margin: 10px;
        }

        .recommendation-item img {
            max-width: 200px;
        }
    </style>
  
  <body class="productPage">  
 
  <%@ include file="../include/header.jsp" %>

  
	<div class="container">
        <div class="title">Tìm kiếm sản phẩm bằng hình ảnh</div>
        <div class="upload-btn-wrapper">
            <button class="btn">Tải hình mẫu của bạn</button>
            <input type="file" name="file" id="fileInput" accept="image/*">
        </div>
        <div class="uploaded-image">
            <img id="uploadedImage" src="" alt="Uploaded Image">
        </div>
        <div class="recommendations">
            <div class="recommendation-item">
                <img src="recommendation1.jpg" alt="Gợi ý 1">
            </div>
            <div class="recommendation-item">
                <img src="recommendation2.jpg" alt="Gợi ý 2">
            </div>
            <div class="recommendation-item">
                <img src="recommendation3.jpg" alt="Gợi ý 3">
            </div>
            <div class="recommendation-item">
                <img src="recommendation4.jpg" alt="Gợi ý 4">
            </div>
            <div class="recommendation-item">
                <img src="recommendation5.jpg" alt="Gợi ý 5">
            </div>
        </div>
    </div>

    <script>
        // JavaScript code to handle file input and display the uploaded image
        const fileInput = document.getElementById('fileInput');
        const uploadedImage = document.getElementById('uploadedImage');

        fileInput.addEventListener('change', function () {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    uploadedImage.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        });
    </script>

   
  <%@ include file = "../include/footer.jsp" %>
     <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="assets/js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="assets/js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="assets/js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- To Slider JS -->
  <script src="assets/js/sequence.js"></script>
  <script src="assets/js/sequence-theme.modern-slide-in.js"></script>  
  <!-- Product view slider -->
  <script type="text/javascript" src="assets/js/jquery.simpleGallery.js"></script>
  <script type="text/javascript" src="assets/js/jquery.simpleLens.js"></script>
  <!-- slick slider -->
  <script type="text/javascript" src="assets/js/slick.js"></script>
  <!-- Price picker slider -->
  <script type="text/javascript" src="assets/js/nouislider.js"></script>
  <!-- Custom js -->
  <script src="assets/js/custom.js"></script> 




	<script src="<c:url value='assets/js/search.js'/>"></script>
  </body>
</html>