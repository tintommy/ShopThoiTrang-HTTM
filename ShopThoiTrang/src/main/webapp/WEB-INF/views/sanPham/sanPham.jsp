<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<link rel="stylesheet" href='<c:url value="/assets/css/product.css"/>' />

<link href="assets/css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href='<c:url value="/assets/css/font-awesome.css"/>' />
<!-- Bootstrap -->
<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap.css"/>' />
<!-- SmartMenus jQuery Bootstrap Addon CSS -->
<link rel="stylesheet"
	href='<c:url value="/assets/css/jquery.smartmenus.bootstrap.css"/>' />
<!-- Product view slider -->
<link rel="stylesheet"
	href='<c:url value="/assets/css/jquery.simpleLens.css"/>' />
<!-- slick slider -->
<link rel="stylesheet" type="text/css"
	href='<c:url value="/assets/css/slick.css"/>' />
<!-- price picker slider -->
<link rel="stylesheet" type="text/css"
	href='<c:url value="/assets/css/nouislider.css"/>' />
<!-- Theme color -->
<link id="switcher" rel="stylesheet"
	href='<c:url value="/assets/css/theme-color/default-theme.css"/>' />
<!-- <link id="switcher" href="css/theme-color/bridge-theme.css" rel="stylesheet"> -->
<!-- Top Slider CSS -->
<link media="all" rel="stylesheet"
	href='<c:url value="/assets/css/sequence-theme.modern-slide-in.css"/>' />

<!-- Main style sheet -->
<link rel="stylesheet" href='<c:url value="/assets/css/style.css"/>' />

<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<!-- SCROLL TOP BUTTON -->
	<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
	<!-- END SCROLL TOP BUTTON -->
	<!-- HEADER -->

	<%@ include file="../include/header.jsp"%>

	<!-- catg header banner section -->
	<!--   <section id="aa-catg-head-banner">
   <img src="assets/img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
   <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
      </div>
     </div>
   </div>
  </section> -->
	<!-- / catg header banner section -->

	<section id="aa-product-details">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-product-details-area">
						<div class="aa-product-details-content">
							<div class="row">

								<!-- Tieu de -->
								<div class="container">
									<div>
										<ol class="breadcrumb">
											<li><a href="${pageContext.servletContext.contextPath}/">Trang
													chủ</a></li>
											<li><a
												href="${pageContext.servletContext.contextPath}/shop.htm">Sản
													phẩm</a></li>
											<li class="active"><a href="shop/${sanPham.maKieu.loai.maLoai}.htm">${sanPham.maKieu.loai.tenLoai}</a></li>
											<li class="active"><a href="shop/${sanPham.maKieu.loai.maLoai}/${sanPham.maKieu.maKieu}.htm">${sanPham.maKieu.tenKieu}</a></li>
										</ol>
									</div>
								</div>

								<!-- Modal view slider -->
								<div class="col-md-5 col-sm-5 col-xs-12">
									<div class="aa-product-view-slider">
										<div id="demo-1" class="simpleLens-gallery-container">
											<div class="simpleLens-container">
												<div class="simpleLens-big-image-container">
													<a data-lens-image="${sanPham.hinhAnh.link}"
														class="simpleLens-lens-image"><img style="width:350px; height:420px;"
														src="${sanPham.hinhAnh.link}" class="simpleLens-big-image"></a>
												</div>
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
										<h1 class="mt-3"
											style="font-size: 2rem; font-weight: bold; color: #3D71B6;">${sanPham.tenSanPham}</h1>
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

											<h5 style="padding-top: 3px;">
												Còn lại: <i>${sanPham.soLuong}</i>
											</h5>
											<h5>
												Đang xem: Size ${sanPham.size}
												</h4>

												<div class="fs-5 my-3 gia">
													<span
														style="font-size: 1.875rem; font-weight: bold; color: #ee4d2d; font-style: normal;"><fmt:formatNumber
															value="${sanPham.donGia}" pattern="#,##0" />đ</span>
												</div>
												<f:form action="themVaoGio/${sanPham.maSP}.htm"
													method="post">
													<div class="d-flex align-items-center">

														<h4>Bảng Size</h4>
														<div class="aa-prod-view-size">
															<div class="size-buttons">
																<c:forEach items="${sizes}" var="size">
																	<%-- <c:set var="trimmedMaSP"
																		value="${fn:substring(sanPham.maSP, 0, fn:length(sanPham.maSP) - 2)}" /> --%>
																		<c:set var="indexOfUnderscore" value="${fn:indexOf(sanPham.maSP, '_')}" />
																	<c:set var="trimmedMaSP" value="${fn:substring(sanPham.maSP, 0, indexOfUnderscore)}" />
																	<c:set var="trimmedSize" value="${fn:trim(size)}" />
																	<c:choose>
																		<c:when test="${sanPham.size == size}">
																			<a
																				href="${pageContext.servletContext.contextPath}/product/${trimmedMaSP}_${trimmedSize}.htm"
																				style="color: black; background-color: #CCFF99; border: solid 2px;">
																				${size} </a>
																		</c:when>
																		<c:otherwise>

																			<a
																				href="${pageContext.servletContext.contextPath}/product/${trimmedMaSP}_${trimmedSize}.htm">
																				${size} </a>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>

															</div>
														</div>

														<h4>Số lượng</h4>
														<input class="form-control text-center me-3"
															id="inputQuantity" type="number" name="soLuong" value="1"
															min="1" inputmode="numeric" autocomplete="off"
															max="${sanPham.soLuong}" style="width: 90px" /> <br>
														<c:choose>
															<c:when test="${sanPham.soLuong == 0 }">
																
																<button name="them" class="btn btn-warning"
																	disabled="disabled">Thêm vào giỏ</button>
																<span style="font-style: italic; color: red;">Sản phẩm này đã hết hàng!</span>
															</c:when>
															<c:otherwise>
																<button name="them" class="btn btn-warning">Thêm
																	vào giỏ</button>

															</c:otherwise>
														</c:choose>

													</div>
												</f:form>
												<span id="thongBao"
													style="font-style: italic; font-size: 0.7rem; color: blue;">${messenger }</span>
													
												<br>	
												<button name="fav" class="btn btn-danger" >
													<a href="themVaoYT/${sanPham.maSP}.htm" style="color:white;">Thêm vào yêu
														thích</a>
												</button>
												
										</c:if>

										<c:if test="${sanPham.trangThai==false }">
											<div class="fs-5 my-3 gia">
												<span
													style="font-size: 1.875rem; font-weight: bold; color: #ee4d2d; font-style: normal; text-decoration: underline;">NGỪNG
													KINH DOANH</span>
											</div>
										</c:if>
										<br>
										<%-- <button name="fav" class="btn btn-danger">
											<a href="themVaoYT/${sanPham.maSP}.htm">Thêm vào yêu
												thích</a>
										</button> --%>
									</div>
								</div>
							</div>
						</div>

						<div class="aa-product-details-bottom">
							<ul class="nav nav-tabs" id="myTab2">
								<li><a href="#description" data-toggle="tab">Mô tả</a></li>
								<li><a href="#review" data-toggle="tab">Đánh giá</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div class="tab-pane fade in active" id="description">
									<p>${sanPham.moTa}</p>
								</div>
								<div class="tab-pane fade " id="review">



									<h4 class="inDam">ĐÁNH GIÁ SẢN PHẨM</h4>
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

										<ul class="aa-review-nav">
											<c:forEach items="${danhGiaList}" var="danhGia">
												<li>
													<div class="media">
														<div class="media-left">
															<a href="#"> <img style="height: 70px; width: 70px;"
																class="media-object" src="assets/img/avatar.png"
																alt="avatar user">
															</a>
														</div>
														<div class="media-body">
															<h4 class="media-heading">
																<strong>${danhGia.nguoiDung.hoTen}</strong> <span>${danhGia.ngay}</span>
															</h4>
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

										<%-- <h4>Thêm đánh giá</h4>
                   <div class="aa-your-rating">
                     <p>Chọn số sao</p>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                     <a href="#"><span class="fa fa-star-o"></span></a>
                   </div>
                   <!-- review form -->
                   <form action="" class="aa-review-form">
                      <div class="form-group">
                        <label for="message">Đánh giá của bạn</label>
                        <textarea class="form-control" rows="3" id="message"></textarea>
                      </div>
                      <div class="form-group">
                        <label for="name">Tên</label>
                        <input type="text" class="form-control" id="name" placeholder="Name">
                      </div>  
                      <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="example@gmail.com">
                      </div>

                      <button type="submit" class="btn btn-default aa-review-submit">Gửi</button>
                   </form> --%>

									</div>

								</div>
							</div>
						</div>



						<!-- Related product -->
						<div class="aa-product-related-item">
							<h4>SẢN PHẨM KHÁC</h4>
							<ul class="aa-product-catg aa-related-item-slider">

								<c:forEach items="${sanPhamCungKieu}" var="sp">

									<li>
										<figure>
											<a class="aa-product-img" href="product/${sp.maSP}.htm"><img
												style="width: 250px; height: 300px;"
												src="${sp.hinhAnh.link}" alt="product img"></a>
											<!-- <a class="aa-add-card-btn" href="#"><span
												class="fa fa-shopping-cart"></span>Add To Cart</a> -->
											<figcaption>
												<h4 class="aa-product-title">
													<a href="product/${sp.maSP}.htm"
														style="font-weight: bold; color: #3D71B6;">${sp.tenSanPham}</a>
												</h4>
												<%-- <span class="aa-product-price">${sp.donGia}đ</span><span class="aa-product-price"><!-- <del>$65.50</del> --></span> --%>
												<span
													style="font-size: 1.875rem; font-weight: bold; color: #ee4d2d; font-style: normal;"><fmt:formatNumber
														value="${sanPham.donGia}" pattern="#,##0" />đ</span>
											</figcaption>
										</figure>

										<!-- <div class="aa-product-hvr-content">
											<a href="#" data-toggle="tooltip" data-placement="top"
												title="Thêm vào giỏ"><span class="fa fa-heart-o"></span></a>
											<a href="#" data-toggle="tooltip" data-placement="top"
												title="So sánh"><span class="fa fa-exchange"></span></a> 
											<a
												href="#" data-toggle2="tooltip" data-placement="top"
												title="Quick View" data-toggle="modal"
												data-target="#quick-view-modal"><span
												class="fa fa-search"></span></a>
										</div> <span class="aa-badge aa-sale" href="#">SALE!</span>  -->
									</li>

								</c:forEach>

							</ul>


							<!-- quick view modal -->
							<c:forEach items="${sanPhamCungKieu}" var="sp">
								<div class="modal fade" id="quick-view-modal" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-body">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<div class="row">
													<!-- Modal view slider -->

													<div class="col-md-6 col-sm-6 col-xs-12">
														<div class="aa-product-view-slider">
															<div class="simpleLens-gallery-container" id="demo-1">
																<div class="simpleLens-container">
																	<div class="simpleLens-big-image-container">
																		<a class="simpleLens-lens-image"
																			data-lens-image="${sp.hinhAnh.link}"> <img
																			src="${sp.hinhAnh.link}" class="simpleLens-big-image">
																		</a>
																	</div>
																</div>
																<!-- <div class="simpleLens-thumbnails-container">
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
                              </div> -->
															</div>
														</div>
													</div>


													<!-- Modal view content -->
													<div class="col-md-6 col-sm-6 col-xs-12">

														<div class="aa-product-view-content">

															<h4>${sp.tenSanPham}</h4>
															
															<%-- <span>${sp.moTa}</span> --%>

															<div class="aa-price-block">
																<span
																	style="font-size: 1.875rem; font-weight: bold; color: #ee4d2d; font-style: normal;"><fmt:formatNumber
																		value="${sanPham.donGia}" pattern="#,##0" />đ</span>
																<!-- <p class="aa-product-avilability">Avilability: <span>In stock</span></p> -->
															</div>
															<p id="moTa" class="truncate-text">${sp.moTa}</p>
															<script>
															    var moTaElement = document.getElementById("moTa");
															    var maxWords = 50; // Số từ tối đa bạn muốn hiển thị
															
															    // Tách nội dung thành các từ và đếm số từ
															    var words = moTaElement.textContent.split(' ');
															    var wordCount = words.length;
															
															    // Nếu số từ vượt quá giới hạn, ẩn nội dung và thêm một link để hiển thị toàn bộ nội dung
															    if (wordCount > maxWords) {
															        moTaElement.classList.remove("truncate-text");
															        moTaElement.innerHTML = moTaElement.textContent.split(' ').slice(0, maxWords).join(' ') + '...';
															    }
															</script>
															
															<!-- <h4>Size</h4>
															<div class="aa-prod-view-size">
																<a href="#">S</a> <a href="#">M</a> <a href="#">L</a> <a
																	href="#">XL</a>
															</div> -->
															
															<div class="aa-prod-quantity">
																<%-- <form action="">
                                <select name="" id="">
                                  <option value="0" selected="1">1</option>
                                  <option value="1">2</option>
                                  <option value="2">3</option>
                                  <option value="3">4</option>
                                  <option value="4">5</option>
                                  <option value="5">6</option>
                                </select>
                              </form> --%>

																<p class="aa-prod-category">
																	Loai: <a href="shop/${sanPham.maKieu.loai.maLoai}.htm">${sp.maKieu.loai.tenLoai}</a>
																</p>
																<p class="aa-prod-category">
																	Kiểu: <a href="shop/${sanPham.maKieu.loai.maLoai}/${sanPham.maKieu.maKieu}.htm">${sp.maKieu.tenKieu}</a>
																</p>
															</div>
															<div class="aa-prod-view-bottom">
																<!-- <a href="#" class="aa-add-to-cart-btn"><span class="fa fa-shopping-cart"></span>Thêm vào giỏ</a> -->
																<a href="product/${sp.maSP}.htm"
																	class="aa-add-to-cart-btn">Xem chi tiết</a>
															</div>

														</div>

													</div>
												</div>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
							</c:forEach>
							<!-- / quick view modal -->

						</div>


					</div>
				</div>
			</div>
		</div>
	</section>




	<%@ include file="../include/footer.jsp"%>

	<script src="<c:url value='assets/js/sanPham.js'/>"></script>
	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='assets/js/bootstrap.js'/>"></script>
	<!-- SmartMenus jQuery plugin -->
	<script type="text/javascript"
		src="<c:url value='assets/js/jquery.smartmenus.js'/>"></script>
	<!-- SmartMenus jQuery Bootstrap Addon -->
	<script type="text/javascript"
		src="<c:url value='assets/js/jquery.smartmenus.bootstrap.js'/>"></script>
	<!-- To Slider JS -->
	<script src="<c:url value='assets/js/sequence.js'/>"></script>
	<script
		src="<c:url value='assets/js/sequence-theme.modern-slide-in.js'/>"></script>
	<!-- Product view slider -->
	<script type="text/javascript"
		src="<c:url value='assets/js/jquery.simpleGallery.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='assets/js/jquery.simpleLens.js'/>"></script>
	<!-- slick slider -->
	<script type="text/javascript"
		src="<c:url value='assets/js/slick.js'/>"></script>
	<!-- Price picker slider -->
	<script type="text/javascript"
		src="<c:url value='assets/js/nouislider.js'/>"></script>
	<!-- Custom js -->
	<script src="<c:url value='assets/js/custom.js'/>"></script>
</body>
</html>
