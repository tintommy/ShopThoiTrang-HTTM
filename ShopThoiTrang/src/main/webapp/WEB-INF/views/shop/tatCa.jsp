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
    <title>Daily Shop | Product</title>
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
  <body class="productPage">  
 
  <%@ include file="../include/header.jsp" %>

   <!-- wpf loader Two -->
    <div id="wpf-loader-two">          
      <div class="wpf-loader-two-inner">
        <span>Loading</span>
      </div>
    </div> 
    <!-- / wpf loader Two -->       
 <!-- SCROLL TOP BUTTON -->
    <a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
  <!-- END SCROLL TOP BUTTON -->


 
  <!-- / menu -->  
 
  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
   <img src="assets/img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
   <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
        <h2>Fashion</h2>
        <ol class="breadcrumb" style="margin-left:43%;">
          <li><a href="ShopThoiTrang.htm">Home</a></li>         
          <li  ><a href="shop.htm" class="active">Shop</a></li>
        </ol>
      </div>
     </div>
   </div>
  </section>
  <!-- / catg header banner section -->

  <!-- product category -->
  <section id="aa-product-category">
    <div class="container">
      <div class="row">
        <div class="col-md-10 col-md-push-3">
          <div class="aa-product-catg-content">
            <div class="aa-product-catg-head">
              <div class="aa-product-catg-head-right">
                <a id="grid-catg" href="#"><span class="fa fa-th"></span></a>
                <a id="list-catg" href="#"><span class="fa fa-list"></span></a>
              </div> 
            </div>
            <div class="aa-product-catg-body">
            <ul class="aa-product-catg">
              
                <c:forEach items="${listSPTrenTrang}" var="sp">
                
                <li>               
                   <figure>
                    <a class="aa-product-img" href="product/${sp.maSP}.htm"><img style="width:250px; height:300px;" src="${sp.hinhAnh.link}" alt="product img"></a>
                 <!--    <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Thêm vào giỏ</a> -->
                     <figcaption>
                      <h4 class="aa-product-title"><a href="product/${sp.maSP}.htm" style="font-weight: bold; color: #3D71B6;">${sp.tenSanPham}</a></h4> 
                          
            	      <span class="aa-product-price"><fmt:formatNumber value="${sp.donGia}"  pattern="#,##0" />đ</span><span class="aa-product-price"></span>
                     
                    </figcaption>
                  </figure>
                  
                </li>
                
                </c:forEach>
           </ul>                                                                      
                               
              <!-- quick view modal -->   
                                
            </div>
            
            
            
              <c:if test = "${totalPages!=0 }">
            <div class="pagination-wrapper" style="margin-left:30%;">
		<nav aria-label="Page navigation example">


			<ul class="pagination">
				<li class="page-item ${currentPage == 0 ? 'disabled' : ''}"><a
					class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
					href="/ShopThoiTrang/shop.htm?page=${currentPage - 1}">Trước</a>
				</li>
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a
						class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
						href="/ShopThoiTrang/shop.htm?page=0">1</a></li>
					<li class="page-item disabled"><a
						class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark">...</a>
					</li>
				</c:if>
				<c:forEach begin="${startPage}" end="${endPage}" var="i">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="${currentPage == i ? 'page-link active rounded-0 mr-3 shadow-sm border-top-0 border-left-0' : 'page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark'}"
						href="/ShopThoiTrang/shop.htm?page=${i}">${i + 1}</a>
					</li>
				</c:forEach>
				<c:if test="${currentPage < totalPages - 2}">
					<li class="page-item disabled"><a
						class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark">...</a>
					</li>
					<li class="page-item"><a
						class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
						href="/ShopThoiTrang/shop.htm?page=${totalPages - 1}">${totalPages}</a>
					</li>
				</c:if>
				<li
					class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
					<a
					class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
					href="/ShopThoiTrang/shop.htm?page=${currentPage + 1}">Sau</a>
				</li>
			</ul>
		</nav>
	</div>
         </c:if>   
              
          </div>
        </div>
        
       
          <div class="col-md-2 col-md-pull-10">
          <aside class="aa-sidebar">
          <form action="shop/${loaiSp}.htm" method="post">
            <!-- single sidebar -->
            <div class="aa-sidebar-widget" >
              <h3>Loại sản phẩm</h3>
              <ul class="aa-catg-nav" >
               
               <c:forEach items="${dsLoai}" var="loai">
              	<li > <a class="text-uppercase font-weight-bold text-danger" href="shop/${loai.maLoai}.htm"> ${loai.tenLoai }</a></li>
              	  <div class="aa-sidebar-widget">
              <!-- <h3>Kiểu dáng</h3> -->
              <div style="max-height: 200px; overflow-y: scroll;"> 
              
              <div > 
            
              <c:forEach items="${dsKieu}" var = "kieu">
              <c:if test ="${kieu.loai==loai}">
              		<%-- <a href="#">${kieu.tenKieu}</a> --%>
              		<div class="form-check">
							<input class="form-check-input" type="checkbox"		value="${kieu.tenKieu}" name="style"
								   id="${kieu.maKieu}" /> <label class="form-check-label" for="${kieu.maKieu}">${kieu.tenKieu}</label>														
					</div>
              </c:if>             	
              </c:forEach> 
              
              </div>
            </div>
            </div>
              	
                </c:forEach>  
              
                 
              </ul>
            </div>
            <!-- single sidebar -->
            <div class="aa-sidebar-widget">
              <h3>Giá</h3>              
              <!-- price range -->
                            <div class="aa-sidebar-price-range">
               
                  <!-- <div id="skipstep" class="noUi-target noUi-ltr noUi-horizontal noUi-background">
                  </div>
                  <span id="skip-value-lower" class="example-val">50.00</span>
                 <span id="skip-value-upper" class="example-val">200.00</span> -->
                 <div id="collapseThree"
										class="accordion-collapse collapse show"
										aria-labelledby="headingThree"
										data-bs-parent="#accordionExample">
										<div class="accordion-body">

											<div class="row mb-3">
												<div>
													<div class="range">
														<input type="range" class="form-range" id="customRange1"
															min="0" max="500000" />
													</div>
					
													<div class="col-8">
														<p class="mb-0">Min</p>
														<div class="form-outline">
															<input type="number" id="typeNumber" name="minPrice"
																class="form-control"
																value="${minPrice != 0 ? minPrice : ''}" />
																
																
														</div>
														
													</div>
												</div>

												<div>
													<div class="range">
														<input type="range" class="form-range" id="customRange2"
															min="0" max="9999999" />
													</div>
													<div class="col-8">
														<p class="mb-0">Max</p>
														<div class="form-outline">
															<input type="number" id="typeNumber1" name="maxPrice"
																class="form-control"
																value="${maxPrice != 10000000 ? maxPrice : ''}" />
																<!-- <h5 class="text-right">K</h5> -->

														</div>
													</div>
												</div>

											</div>

										</div>
									</div>
                 
                 <button class="aa-filter-btn" name="btnApply" type="submit">Lọc</button>
               
              </div>                         

            </div>   
             </form>
          </aside>
        </div>
      
      </div>
    </div>
    <br>
    <br>
    
  </section>
  <!-- / product category -->


   
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
  <script src="<c:url value='assets/js/loaiSanPham.js'/>"></script>
  </body>
</html>