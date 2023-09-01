<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Sản Phẩm</title>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
	<%-- <link rel="stylesheet" href='<c:url value="/assets/css/product.css"/>' /> --%>
	
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
<body>
	<!-- HEADER -->
	<%@ include file="../include/header.jsp" %>

	<!-- catg header banner section -->
<%--   <section id="aa-catg-head-banner">
   <img src="assets/img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
   <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
        <h2>T-Shirt</h2>
        <ol class="breadcrumb">
          <li><a href="#">Home</a></li>         
          <li><a href="#">Product</a></li>
          <li class="active">${sanPham.tenSanPham}</li>
        </ol>
      </div>
     </div>
   </div>
  </section> --%>
  <!-- / catg header banner section -->
<div class="container">
      <div>
        <h2>T-Shirt</h2>
        <ol class="breadcrumb">
          <li><a href="#">Home</a></li>         
          <li><a href="#">Product</a></li>
          <li class="active">${sanPham.tenSanPham}</li>
        </ol>
      </div>
     </div>
  <!-- product category -->
  <section id="aa-product-details">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="aa-product-details-area">
            <div class="aa-product-details-content">
              <div class="row">
                <!-- Modal view slider -->
                <div class="col-md-5 col-sm-5 col-xs-12">                              
                  <div class="aa-product-view-slider">                                
                    <div id="demo-1" class="simpleLens-gallery-container">
                      <div class="simpleLens-container">
                        <div class="simpleLens-big-image-container"><a data-lens-image="img/view-slider/large/polo-shirt-1.png" class="simpleLens-lens-image"><img src="${sanPham.hinhAnh.link}" class="simpleLens-big-image"></a></div>
                      </div>
                      <!-- <div class="simpleLens-thumbnails-container">
                          <a data-big-image="img/view-slider/medium/polo-shirt-1.png" data-lens-image="img/view-slider/large/polo-shirt-1.png" class="simpleLens-thumbnail-wrapper" href="#">
                            <img src="img/view-slider/thumbnail/polo-shirt-1.png">
                          </a>                                    
                          <a data-big-image="img/view-slider/medium/polo-shirt-3.png" data-lens-image="img/view-slider/large/polo-shirt-3.png" class="simpleLens-thumbnail-wrapper" href="#">
                            <img src="img/view-slider/thumbnail/polo-shirt-3.png">
                          </a>
                          <a data-big-image="img/view-slider/medium/polo-shirt-4.png" data-lens-image="img/view-slider/large/polo-shirt-4.png" class="simpleLens-thumbnail-wrapper" href="#">
                            <img src="img/view-slider/thumbnail/polo-shirt-4.png">
                          </a>
                      </div> -->
                    </div>
                  </div>
                </div>
                <!-- Modal view content -->
                <div class="col-md-7 col-sm-7 col-xs-12">
                  <div class="aa-product-view-content">
                    <h1 class="mt-3" style="font-size: 2rem; font-weight: bold; color: #3D71B6;">${sanPham.tenSanPham}</h1>
                    <div class="product-rating-stars">
							<c:if test="${sanPham.soSaoTB == 0}">
								<span class="soSaotb"><i>Chưa có đánh giá<i></span>
							</c:if>
							<c:if test="${sanPham.soSaoTB > 0}">
								<span class="soSaotb"><i>Đánh giá:
										${sanPham.soSaoTB}/5 <i class="fas fa-star"></i><i></span>
							</c:if>
						</div>          
                    
                    <c:if test="${sanPham.trangThai==true }">

							<h6 style="padding-top: 3px;">
								Còn lại: <i>${sanPham.soLuong}</i>
							</h6>
							<div class="fs-5 my-3 gia">
								<span
									style="font-size: 1.875rem; font-weight: bold; color: #ee4d2d; font-style: normal;"><fmt:formatNumber
										value="${sanPham.donGia}" pattern="#,##0" />đ</span>
							</div>
							<f:form action="themVaoGio/${sanPham.maSP}.htm" method="post">
								<div class="d-flex align-items-center">
								
								<h4>Size</h4>
			                    <div class="aa-prod-view-size">
			                      <a href="#">S</a>
			                      <a href="#">M</a>
			                      <a href="#">L</a>
			                      <a href="#">XL</a>
			                    </div>
									<!-- <button class="btn btn-outline-dark btn-sm me-3" type="button"
										id="decreaseBtn">
										<i class="bi bi-dash">-</i>
									</button> -->
									
									<h4>Số lượng</h4>
									<input class="form-control text-center me-3" id="inputQuantity"
										type="number" name="soLuong" value="1" min="1" inputmode="numeric" autocomplete="off"
										max="${sanPham.soLuong }" style="width: 90px" />

									<!-- <button class="btn btn-outline-dark btn-sm me-3" type="button"
										id="increaseBtn">
										<i class="bi bi-plus">+</i>
									</button> -->	
									
									<br>					
									<button name="them" class="btn btn-danger">Thêm vào giỏ</button>
								</div>
							</f:form>
							<span id="thongBao"
								style="font-style: italic; font-size: 0.7rem; color: blue;">${messenger }</span>
						</c:if>

						<c:if test="${sanPham.trangThai==false }">
							<div class="fs-5 my-3 gia">
								<span
									style="font-size: 1.875rem; font-weight: bold; color: #ee4d2d; font-style: normal; text-decoration: underline;">NGỪNG
									KINH DOANH</span>
							</div>
						</c:if>
						
						
                    <!-- <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis animi, veritatis quae repudiandae quod nulla porro quidem, itaque quis quaerat!</p> -->
                    
                    
                    <!-- <h4>Color</h4>
                    <div class="aa-color-tag">
                      <a href="#" class="aa-color-green"></a>
                      <a href="#" class="aa-color-yellow"></a>
                      <a href="#" class="aa-color-pink"></a>                      
                      <a href="#" class="aa-color-black"></a>
                      <a href="#" class="aa-color-white"></a>                      
                    </div> -->
                    
                    <%-- <div class="aa-prod-quantity">
                      <form action="">
                        <select id="" name="">
                          <option selected="1" value="0">1</option>
                          <option value="1">2</option>
                          <option value="2">3</option>
                          <option value="3">4</option>
                          <option value="4">5</option>
                          <option value="5">6</option>
                        </select>
                      </form>
                      <p class="aa-prod-category">
                        Category: <a href="#">Polo T-Shirt</a>
                      </p>
                    </div>
                    <div class="aa-prod-view-bottom">
                      <a class="aa-add-to-cart-btn" href="#">Add To Cart</a>
                      <a class="aa-add-to-cart-btn" href="#">Wishlist</a>
                      <a class="aa-add-to-cart-btn" href="#">Compare</a>
                    </div> --%>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="aa-product-details-bottom">
              <ul class="nav nav-tabs" id="myTab2">
                <li><a href="#description" data-toggle="tab">Description</a></li>
                <li><a href="#review" data-toggle="tab">Reviews</a></li>                
              </ul>

              <!-- Tab panes -->
              <div class="tab-content">
                <div class="tab-pane fade in active" id="description">
                  <p>${sanPham.moTa}</p>
                </div>
                <div class="tab-pane fade " id="review">
                 <div class="aa-product-review-area">
                   <h4>2 Reviews for T-Shirt</h4> 
                   <ul class="aa-review-nav">
                     <li>
                        <div class="media">
                          <div class="media-left">
                            <a href="#">
                              <img class="media-object" src="img/testimonial-img-3.jpg" alt="girl image">
                            </a>
                          </div>
                          <div class="media-body">
                            <h4 class="media-heading"><strong>Marla Jobs</strong> - <span>March 26, 2016</span></h4>
                            <div class="aa-product-rating">
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star-o"></span>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                          </div>
                        </div>
                      </li>
                      <li>
                        <div class="media">
                          <div class="media-left">
                            <a href="#">
                              <img class="media-object" src="img/testimonial-img-3.jpg" alt="girl image">
                            </a>
                          </div>
                          <div class="media-body">
                            <h4 class="media-heading"><strong>Marla Jobs</strong> - <span>March 26, 2016</span></h4>
                            <div class="aa-product-rating">
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star"></span>
                              <span class="fa fa-star-o"></span>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                          </div>
                        </div>
                      </li>
                   </ul>
                   <h4>Add a review</h4>
                   <div class="aa-your-rating">
                     <p>Your Rating</p>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                   </div>
                   <!-- review form -->
                   <form action="" class="aa-review-form">
                      <div class="form-group">
                        <label for="message">Your Review</label>
                        <textarea class="form-control" rows="3" id="message"></textarea>
                      </div>
                      <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" placeholder="Name">
                      </div>  
                      <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="example@gmail.com">
                      </div>

                      <button type="submit" class="btn btn-default aa-review-submit">Submit</button>
                   </form>
                 </div>
                </div>            
              </div>
            </div>
            
<%--             <!--Danh gia san pham-->
		<div class="product-comment-list">
			<h3 class="inDam">ĐÁNH GIÁ SẢN PHẨM</h3>
			<div class="product-rating">
				<div class="product-rating-stars">
					<c:if test="${sanPham.soSaoTB == 0}">
						<span class="soSaotb"><i>Chưa có đánh giá<i></span>
					</c:if>
					<c:if test="${sanPham.soSaoTB > 0}">
						<span class="rating-count">Có ${count} đánh giá</span>
						<br>
						<span class="soSaotb"><i>Đánh giá trung bình:
								${sanPham.soSaoTB}/5 <i class="fas fa-star"></i><i></span>
					</c:if>
				</div>
			</div>

			<br>
			<c:forEach items="${danhGiaList}" var="danhGia">
				<div class="product-comment mb-4">
					<div class="info-user">
						<div id="hinh" class="commenter-image"></div>
						<div id="ten" class="commenter-name fs-md c-primary">${danhGia.nguoiDung.hoTen}</div>

						<div class="danhGia">
							<div class="commenter-info">
								<div class="comment-rank c-support">
									<c:if test="${danhGia.soSao == 1}">
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 2}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 3}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 4}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 5}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
								</div>
								<div class="commenter-date">${danhGia.ngay}</div>
							</div>

						</div>

					</div>
					<div id="cmt" class="commenter-comment bgc-bright p-2-2">${danhGia.noiDung}</div>
				</div>
			</c:forEach>
		</div> --%>
		
		<h3 class="inDam">ĐÁNH GIÁ SẢN PHẨM</h3>
		<div class="aa-product-review-area">
				<div class="product-rating-stars">
					<c:if test="${sanPham.soSaoTB == 0}">
						<span class="soSaotb"><i>Chưa có đánh giá<i></span>
					</c:if>
					<c:if test="${sanPham.soSaoTB > 0}">
						<span class="rating-count">Có ${count} đánh giá</span>
						<br>
						<span class="soSaotb"><i>Đánh giá trung bình:
								${sanPham.soSaoTB}/5 <i class="fas fa-star"></i><i></span>
					</c:if>
				</div>
<%-- 		<h4>${count} Reviews for T-Shirt</h4>  --%>
                   <ul class="aa-review-nav">
                   <c:forEach items="${danhGiaList}" var="danhGia">
                     <li>
                        <div class="media">
                          <div class="media-left">
                            <a href="#">
                              <img style="height: 70px; width: 70px;" class="media-object" src="assets/img/avatar.png" alt="avatar user">
                            </a>
                          </div>
                          <div class="media-body">
                            <h4 class="media-heading"><strong>${danhGia.nguoiDung.hoTen}</strong>
                            <span>${danhGia.ngay}</span></h4>
                            <div class="comment-rank c-support">
									<c:if test="${danhGia.soSao == 1}">
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 2}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 3}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 4}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
									<c:if test="${danhGia.soSao == 5}">
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
										<i class="fas fa-star"></i>
									</c:if>
								</div>
                            <p>${danhGia.noiDung}</p>
                          </div>
                        </div>
                      </li>
                      </c:forEach>
                   </ul>
                   </div>
  <!-- / product category -->
  
  
		<!-- Related product -->
            <div class="aa-product-related-item">
              <h3>Related Products</h3>
              <ul class="aa-product-catg aa-related-item-slider">
                <!-- start single product item -->
                <c:forEach items="${sanPhamNgauNhien}" var="sp">
                <li>
                
                  <figure>
                    <a class="aa-product-img" href="#"><img src="${sp.hinhAnh.link}" alt="polo shirt img"></a>
                    <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                     <figcaption>
                      <h4 class="aa-product-title"><a href="#">${sp.tenSanPham}</a></h4>
                      <span class="aa-product-price">${sp.donGia}</span><span class="aa-product-price"><!-- <del>$65.50</del> --></span>
                    </figcaption>
                  </figure>                     
                  <div class="aa-product-hvr-content">
                    <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                    <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                    <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>                            
                  </div>
                  <!-- product badge -->
                  <span class="aa-badge aa-sale" href="#">SALE!</span>
                  	
                </li>
                </c:forEach>
                                                                                 
              </ul>
              
              
              <!-- quick view modal -->                  
              <div class="modal fade" id="quick-view-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">                      
                    <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      <div class="row">
                        <!-- Modal view slider -->
                        <div class="col-md-6 col-sm-6 col-xs-12">                              
                          <div class="aa-product-view-slider">                                
                            <div class="simpleLens-gallery-container" id="demo-1">
                              <div class="simpleLens-container">
                                  <div class="simpleLens-big-image-container">
                                      <a class="simpleLens-lens-image" data-lens-image="img/view-slider/large/polo-shirt-1.png">
                                          <img src="img/view-slider/medium/polo-shirt-1.png" class="simpleLens-big-image">
                                      </a>
                                  </div>
                              </div>
                              <div class="simpleLens-thumbnails-container">
                                  <a href="#" class="simpleLens-thumbnail-wrapper"
                                     data-lens-image="img/view-slider/large/polo-shirt-1.png"
                                     data-big-image="img/view-slider/medium/polo-shirt-1.png">
                                      <img src="img/view-slider/thumbnail/polo-shirt-1.png">
                                  </a>                                    
                                  <a href="#" class="simpleLens-thumbnail-wrapper"
                                     data-lens-image="img/view-slider/large/polo-shirt-3.png"
                                     data-big-image="img/view-slider/medium/polo-shirt-3.png">
                                      <img src="img/view-slider/thumbnail/polo-shirt-3.png">
                                  </a>

                                  <a href="#" class="simpleLens-thumbnail-wrapper"
                                     data-lens-image="img/view-slider/large/polo-shirt-4.png"
                                     data-big-image="img/view-slider/medium/polo-shirt-4.png">
                                      <img src="img/view-slider/thumbnail/polo-shirt-4.png">
                                  </a>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!-- Modal view content -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <div class="aa-product-view-content">
                            <h3>T-Shirt</h3>
                            <div class="aa-price-block">
                              <span class="aa-product-view-price">$34.99</span>
                              <p class="aa-product-avilability">Avilability: <span>In stock</span></p>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis animi, veritatis quae repudiandae quod nulla porro quidem, itaque quis quaerat!</p>
                            <h4>Size</h4>
                            <div class="aa-prod-view-size">
                              <a href="#">S</a>
                              <a href="#">M</a>
                              <a href="#">L</a>
                              <a href="#">XL</a>
                            </div>
                            <div class="aa-prod-quantity">
                              <form action="">
                                <select name="" id="">
                                  <option value="0" selected="1">1</option>
                                  <option value="1">2</option>
                                  <option value="2">3</option>
                                  <option value="3">4</option>
                                  <option value="4">5</option>
                                  <option value="5">6</option>
                                </select>
                              </form>
                              <p class="aa-prod-category">
                                Category: <a href="#">Polo T-Shirt</a>
                              </p>
                            </div>
                            <div class="aa-prod-view-bottom">
                              <a href="#" class="aa-add-to-cart-btn"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                              <a href="#" class="aa-add-to-cart-btn">View Details</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>                        
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div>
              <!-- / quick view modal -->   
                        
            </div> 
            
             
          </div>
        </div>
      </div>
    </div>
  </section>
  
  

	
	<%@ include file="../include/footer.jsp" %>
	
	<script src="<c:url value='assets/js/sanPham.js'/>"></script>
	<!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- To Slider JS -->
  <script src="js/sequence.js"></script>
  <script src="js/sequence-theme.modern-slide-in.js"></script>  
  <!-- Product view slider -->
  <script type="text/javascript" src="js/jquery.simpleGallery.js"></script>
  <script type="text/javascript" src="js/jquery.simpleLens.js"></script>
  <!-- slick slider -->
  <script type="text/javascript" src="js/slick.js"></script>
  <!-- Price picker slider -->
  <script type="text/javascript" src="js/nouislider.js"></script>
  <!-- Custom js -->
  <script src="js/custom.js"></script> 
</body>
</html>
