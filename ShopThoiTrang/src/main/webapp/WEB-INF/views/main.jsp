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
<title>Trang Chủ</title>


<link href="assets/css/font-awesome.css" rel="stylesheet">
<!-- Bootstrap -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!-- SmartMenus jQuery Bootstrap Addon CSS -->
<link href="assets/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
<!-- Product view slider -->
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.simpleLens.css">
<!-- slick slider -->
<link rel="stylesheet" type="text/css" href="assets/css/slick.css">
<!-- price picker slider -->
<link rel="stylesheet" type="text/css" href="assets/css/nouislider.css">
<!-- Theme color -->
<link id="switcher" href="assets/css/theme-color/default-theme.css"
	rel="stylesheet">
<!-- <link id="switcher" href="css/theme-color/bridge-theme.css" rel="stylesheet"> -->
<!-- Top Slider CSS -->
<link href="assets/css/sequence-theme.modern-slide-in.css"
	rel="stylesheet" media="all">

<!-- Main style sheet -->
<link href="assets/css/style.css" rel="stylesheet">

<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>



<style>
.sanPham:hover {
	border: 2px solid red;
	box-shadow: 0 0 10px rgba(98, 40, 40, 0.7)
}
</style>


</head>
<body>
	<%@ include file="include/header.jsp"%>
	<!-- Start slider -->
	<section id="aa-slider">
		<div class="aa-slider-area">
			<div id="sequence" class="seq">
				<div class="seq-screen">
					<ul class="seq-canvas">
						<!-- single slide item -->
						<li>
							<div class="seq-model">
								<img data-seq src="assets/img/slider/1.jpg" alt="Men slide img" />
							</div>
							<div class="seq-title">
								<span data-seq>Giảm Đến 75%</span>
								<h2 data-seq>Thời Trang Nam</h2>
								<p data-seq>Các bộ trang phục độc lạ dành cho quý ông</p>

							</div>
						</li>
						<!-- single slide item -->
						<li>
							<div class="seq-model">
								<img data-seq src="assets/img/slider/2.jpg"
									alt="Wristwatch slide img" />
							</div>
							<div class="seq-title">
								<span data-seq>Giảm Đến 50%</span>
								<h2 data-seq>Thời Trang Nữ</h2>
								<p data-seq>các mẫu quần áo mới dành cho quý bà</p>

							</div>
						</li>
						<!-- single slide item -->
						<li>
							<div class="seq-model">
								<img data-seq src="assets/img/slider/1.jpg" alt="Men slide img" />
							</div>
							<div class="seq-title">
								<span data-seq>Giảm Đến 75%</span>
								<h2 data-seq>Thời Trang Nam</h2>
								<p data-seq>Các bộ trang phục độc lạ dành cho quý ông</p>

							</div>
						</li>
						<!-- single slide item -->
						<li>
							<div class="seq-model">
								<img data-seq src="assets/img/slider/2.jpg"
									alt="Wristwatch slide img" />
							</div>
							<div class="seq-title">
								<span data-seq>Giảm Đến 50%</span>
								<h2 data-seq>Thời Trang Nữ</h2>
								<p data-seq>các mẫu quần áo mới dành cho quý bà</p>

							</div>
						</li>

						<!-- single slide item -->
						<li>
							<div class="seq-model">
								<img data-seq src="assets/img/slider/1.jpg" alt="Men slide img" />
							</div>
							<div class="seq-title">
								<span data-seq>Giảm Đến 75%</span>
								<h2 data-seq>Thời Trang Nam</h2>
								<p data-seq>Các bộ trang phục độc lạ dành cho quý ông</p>

							</div>
						</li>
						<!-- single slide item -->
						<li>
							<div class="seq-model">
								<img data-seq src="assets/img/slider/2.jpg"
									alt="Wristwatch slide img" />
							</div>
							<div class="seq-title">
								<span data-seq>Giảm Đến 50%</span>
								<h2 data-seq>Thời Trang Nữ</h2>
								<p data-seq>các mẫu quần áo mới dành cho quý bà</p>

							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<!-- / slider -->
<br>
<br>
	<!-- Products section -->
	<section id="aa-product">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="aa-product-area">
							<div class="aa-product-inner">
								<!-- start prduct navigation -->
								<ul class="nav nav-tabs aa-products-tab">
									<li class="active"><a href="#men" data-toggle="tab">Nam</a></li>
									<li><a href="#women" data-toggle="tab">Nữ</a></li>

								</ul>
								<!-- Tab panes -->
								<div class="tab-content">
									<!-- Start men product category -->
									<div class="tab-pane fade in active" id="men">
										<ul class="aa-product-catg">
											<!-- start single product item -->
											<c:forEach var="sp" items="${listSpNam}">
												<li class="sanPham">
													<figure>
														<a class="aa-product-img" href="product/${sp.maSP}.htm"><img
															style="width: 250px; height: 300px;"
															src="${sp.hinhAnh.link}" alt="product img"></a>
														<!--    <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Thêm vào giỏ</a> -->
														<figcaption>
															<h4 class="aa-product-title">
																<a href="product/${sp.maSP}.htm"
																	style="font-weight: bold; color: #3D71B6;">${sp.tenSanPham}</a>
															</h4>

															<span class="aa-product-price"><fmt:formatNumber
																	value="${sp.donGia}" pattern="#,##0" />đ</span><span
																class="aa-product-price"></span>

														</figcaption>
													</figure> <!-- <span class="aa-badge aa-sale" href="#">SALE!</span>  -->
												</li>

											</c:forEach>

										</ul>
										<a class="aa-browse-btn" href="shop.htm">Xem thêm <span class="fa fa-long-arrow-right"></span>
										</a>

									</div>
									<!-- / men product category -->
									<!-- start women product category -->
									<div class="tab-pane fade" id="women">
										<ul class="aa-product-catg">
											<!-- start single product item -->
											<c:forEach var="sp" items="${listSpNu}">
												<li class="sanPham">
													<figure>
														<a class="aa-product-img" href="product/${sp.maSP}.htm"><img
															style="width: 250px; height: 300px;"
															src="${sp.hinhAnh.link}" alt="product img"></a>
														<!--    <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Thêm vào giỏ</a> -->
														<figcaption>
															<h4 class="aa-product-title">
																<a href="product/${sp.maSP}.htm"
																	style="font-weight: bold; color: #3D71B6;">${sp.tenSanPham}</a>
															</h4>

															<span class="aa-product-price"><fmt:formatNumber
																	value="${sp.donGia}" pattern="#,##0" />đ</span><span
																class="aa-product-price"></span>

														</figcaption>
													</figure> <!-- <span class="aa-badge aa-sale" href="#">SALE!</span>  -->
												</li>

											</c:forEach>

										</ul>
										<a class="aa-browse-btn" href="shop.htm">Xem thêm  <span class="fa fa-long-arrow-right"></span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<hr width="50%">
	<!-- popular section -->
	<section id="aa-popular-category">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="aa-popular-category-area">
							<!-- start prduct navigation -->
							<ul class="nav nav-tabs aa-products-tab">
								<li class="active"><a href="#popular" data-toggle="tab">Đề
										xuất cho bạn</a></li>

								<li><a href="#latest" data-toggle="tab">Mới Nhất</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<!-- Start men popular category -->
								<div class="tab-pane fade in active" id="popular">
									<ul class="aa-product-catg aa-popular-slider">
										<!-- start single product item -->
										<c:forEach items="${sanPhamNgauNhien}" var="sp">
										<li>
											<figure>
												<a class="aa-product-img" href="product/${sp.maSP}.htm"><img
													src="${sp.hinhAnh.link}" style="width:250px; height:300px;"alt="product img"></a>
												<!-- <a class="aa-add-card-btn" href="#"><span
													class="fa fa-shopping-cart"></span>Add To Cart</a> -->
												<figcaption>
												<h4 class="aa-product-title">
													<a href="product/${sp.maSP}.htm"
														style="font-weight: bold; color: #3D71B6;">${sp.tenSanPham}</a>
												</h4>
												<%-- <span class="aa-product-price">${sp.donGia}đ</span><span class="aa-product-price"><!-- <del>$65.50</del> --></span> --%>
												<span class="aa-product-price"><fmt:formatNumber
																	value="${sp.donGia}" pattern="#,##0" />đ</span><span
																class="aa-product-price"></span>
											</figcaption>
											</figure>
											<!-- <div class="aa-product-hvr-content">
												<a href="#" data-toggle="tooltip" data-placement="top"
													title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
												<a href="#" data-toggle="tooltip" data-placement="top"
													title="Compare"><span class="fa fa-exchange"></span></a> <a
													href="#" data-toggle2="tooltip" data-placement="top"
													title="Quick View" data-toggle="modal"
													data-target="#quick-view-modal"><span
													class="fa fa-search"></span></a>
											</div> product badge <span class="aa-badge aa-sale"
											href="#">SALE!</span> -->
										</li>
										</c:forEach>
									</ul>
									<a class="aa-browse-btn" href="#">Xem thêm  <span
										class="fa fa-long-arrow-right"></span></a>
								</div>
								<!-- / popular product category -->

								<!--     start featured product category
                <div class="tab-pane fade" id="featured">
                 <ul class="aa-product-catg aa-featured-slider">
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/man/polo-shirt-2.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                         <figcaption>
                          <h4 class="aa-product-title"><a href="#">Polo T-Shirt</a></h4>
                          <span class="aa-product-price">$45.50</span><span class="aa-product-price"><del>$65.50</del></span>
                        </figcaption>
                      </figure>                     
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>                            
                      </div>
                      product badge
                      <span class="aa-badge aa-sale" href="#">SALE!</span>
                    </li>
                     start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/women/girl-2.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                        <figcaption>
                          <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                          <span class="aa-product-price">$45.50</span>
                        </figcaption>
                      </figure>                      
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                      product badge
                       <span class="aa-badge aa-sold-out" href="#">Sold Out!</span>
                    </li>
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/man/t-shirt-1.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                      </figure>
                      <figcaption>
                        <h4 class="aa-product-title"><a href="#">T-Shirt</a></h4>
                        <span class="aa-product-price">$45.50</span>
                      </figcaption>
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                      product badge
                       <span class="aa-badge aa-sold-out" href="#">Sold Out!</span>
                    </li>
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/women/girl-3.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                         <figcaption>
                          <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                          <span class="aa-product-price">$45.50</span><span class="aa-product-price"><del>$65.50</del></span>
                        </figcaption>
                      </figure>                     
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                    </li>
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/man/polo-shirt-1.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                        <figcaption>
                          <h4 class="aa-product-title"><a href="#">Polo T-Shirt</a></h4>
                          <span class="aa-product-price">$45.50</span><span class="aa-product-price"><del>$65.50</del></span>
                        </figcaption>
                      </figure>                      
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                    </li>
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/women/girl-4.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                        <figcaption>
                          <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                          <span class="aa-product-price">$45.50</span><span class="aa-product-price"><del>$65.50</del></span>
                        </figcaption>
                      </figure>                     
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                      product badge
                      <span class="aa-badge aa-hot" href="#">HOT!</span>
                    </li>    
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/man/polo-shirt-4.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                         <figcaption>
                          <h4 class="aa-product-title"><a href="#">Polo T-Shirt</a></h4>
                          <span class="aa-product-price">$45.50</span><span class="aa-product-price"><del>$65.50</del></span>
                        </figcaption>
                      </figure>                     
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                      product badge
                      <span class="aa-badge aa-hot" href="#">HOT!</span>
                    </li> 
                    start single product item
                    <li>
                      <figure>
                        <a class="aa-product-img" href="#"><img src="img/women/girl-1.png" alt="polo shirt img"></a>
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                         <figcaption>
                          <h4 class="aa-product-title"><a href="#">This is Title</a></h4>
                          <span class="aa-product-price">$45.50</span><span class="aa-product-price"><del>$65.50</del></span>
                        </figcaption>
                      </figure>                     
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                        <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span class="fa fa-exchange"></span></a>
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>                            
                      </div>
                      product badge
                      <span class="aa-badge aa-sale" href="#">SALE!</span>
                    </li>                                                                                   
                  </ul>
                  <a class="aa-browse-btn" href="#">Browse all Product <span class="fa fa-long-arrow-right"></span></a>
                </div>
                / featured product category
 -->
								<!-- start latest product category -->
								<div class="tab-pane fade" id="latest">
									<ul class="aa-product-catg aa-latest-slider">
										<!-- start single product item -->
										<c:forEach items="${sanPhamMoi}" var="sp">
										<li>
											<figure>
												<a class="aa-product-img" href="product/${sp.maSP}.htm"><img
													src="${sp.hinhAnh.link}" style="width:250px; height:300px;"alt="product img"></a>
												<!-- <a class="aa-add-card-btn" href="#"><span
													class="fa fa-shopping-cart"></span>Add To Cart</a> -->
												<figcaption>
												<h4 class="aa-product-title">
													<a href="product/${sp.maSP}.htm"
														style="font-weight: bold; color: #3D71B6;">${sp.tenSanPham}</a>
												</h4>
												<%-- <span class="aa-product-price">${sp.donGia}đ</span><span class="aa-product-price"><!-- <del>$65.50</del> --></span> --%>
												<span class="aa-product-price"><fmt:formatNumber
																	value="${sp.donGia}" pattern="#,##0" />đ</span><span
																class="aa-product-price"></span>
											</figcaption>
											</figure>
											<!-- <div class="aa-product-hvr-content">
												<a href="#" data-toggle="tooltip" data-placement="top"
													title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
												<a href="#" data-toggle="tooltip" data-placement="top"
													title="Compare"><span class="fa fa-exchange"></span></a> <a
													href="#" data-toggle2="tooltip" data-placement="top"
													title="Quick View" data-toggle="modal"
													data-target="#quick-view-modal"><span
													class="fa fa-search"></span></a>
											</div> product badge <span class="aa-badge aa-sale"
											href="#">SALE!</span> -->
										</li>
										</c:forEach>
										
									</ul>
									<a class="aa-browse-btn" href="#">Xem thêm <span
										class="fa fa-long-arrow-right"></span></a>
								</div>
								<!-- / latest product category -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / popular section -->
	<!-- Support section -->
	<section id="aa-support">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-support-area">
						<!-- single support -->
						<div class="col-md-4 col-sm-4 col-xs-12">
							<div class="aa-support-single">
								<span class="fa fa-truck"></span>
								<h4>Miễn Phí Giao Hàng</h4>

							</div>
						</div>
						<!-- single support -->
						<div class="col-md-4 col-sm-4 col-xs-12">
							<div class="aa-support-single">
								<span class="fa fa-clock-o"></span>
								<h4>30 ngày hoàn tiền</h4>

							</div>
						</div>
						<!-- single support -->
						<div class="col-md-4 col-sm-4 col-xs-12">
							<div class="aa-support-single">
								<span class="fa fa-phone"></span>
								<h4>Tư vấn 24/7</h4>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / Support section -->
	<!-- Testimonial -->




	<%@ include file="include/footer.jsp"%>

	<!-- Login Modal -->
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4>Đăng Nhập</h4>
					<f:form class="aa-login-form" action="form/login.htm"
						modelAttribute="user" method="post">
						<label for="">Username hoặc Email <span>*</span></label>
						<f:input type="text" path="userName" placeholder="Username/email" />
						<label for="">Mật khẩu <span>*</span></label>
						<f:input type="password" path="passWord" placeholder="Mật khẩu" />
						<button class="aa-browse-btn" type="submit" name="login">Đăng
							nhập</button>
						<label for="rememberme" class="rememberme"><input
							type="checkbox" id="rememberme"> Remember me </label>
						<p class="aa-lost-password">
							<a href="forgotpass.htm">Quên mật khẩu?</a>
						</p>
						<div class="aa-register-now">
							Chưa có tài khoản?<a href="signup.htm">Đăng kí ngay!</a>
						</div>
					</f:form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- SmartMenus jQuery plugin -->
	<script type="text/javascript" src="assets/js/jquery.smartmenus.js"></script>
	<!-- SmartMenus jQuery Bootstrap Addon -->
	<script type="text/javascript"
		src="assets/js/jquery.smartmenus.bootstrap.js"></script>
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

</body>
</html>