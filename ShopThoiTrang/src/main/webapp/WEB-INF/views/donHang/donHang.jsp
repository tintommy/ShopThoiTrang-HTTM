<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Daily Shop | Cart Page</title>

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


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<style type="text/css">
.disable {
	pointer-events: none;
	color: black !important;
	background-color: gray !important;
}
</style>

</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<!-- SCROLL TOP BUTTON -->
	<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
	<!-- END SCROLL TOP BUTTON -->



	<!-- catg header banner section -->

	<div class="aa-catg-head-banner-area">
		<div class="container">
			<div class="aa-catg-head-banner-content">
				<h2>Đặt hàng</h2>
				<ol class="breadcrumb">
					<li><a href="index.html">Home</a></li>
					<li class="active">Đặt hàng</li>
				</ol>
			</div>
		</div>
	</div>
	</section>
	<!-- / catg header banner section -->

	<!-- Cart view section -->
	<section id="checkout">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="checkout-area">
						
							<div class="row">
								<div class="col-md-8">
									<div class="checkout-left">
										<div class="panel-group" id="accordion">


											<!-- Billing Details -->
											<div class="panel panel-default aa-checkout-billaddress">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseThree"> Địa chỉ giao hàng </a>
													</h4>
												</div>
												<div id="collapseThree" class="panel-collapse collapse">
													<div class="panel-body">
														<c:if test="${empty sessionScope.NEWINFO}">
															<div class="row">
																<div class="col-md-6">
																	<div class="aa-checkout-single-bill">
																		<span><b>Họ Tên:</b> ${USER.hoTen } </span>
																	</div>
																</div>
																<div class="col-md-6">
																	<div class="aa-checkout-single-bill">
																		<span> <b>SĐT: </b> ${USER.sdt}
																		</span>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<div class="aa-checkout-single-bill">
																		<span> <b>Địa Chỉ: </b> ${USER.diaChi}
																		</span>
																	</div>
																</div>
															</div>
														</c:if>

														<c:if test="${not empty sessionScope.NEWINFO}">
															<div class="row">
																<div class="col-md-6">
																	<div class="aa-checkout-single-bill">
																		<span><b>Họ Tên:</b> ${NEWINFO.hoTen } </span>
																	</div>
																</div>
																<div class="col-md-6">
																	<div class="aa-checkout-single-bill">
																		<span> <b>SĐT: </b> ${NEWINFO.sdt}
																		</span>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<div class="aa-checkout-single-bill">
																		<span> <b>Địa Chỉ: </b> ${NEWINFO.diaChi}
																		</span>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<a href="diaChiMacDinh.htm"
																		style="background-color: #FF6666; color: white; padding: 10px; cursor: pointer;">Quay
																		về địa chỉ mặc định</a>
																</div>
															</div>
														</c:if>

													</div>
												</div>
											</div>
											<!-- Shipping Address -->
											<div class="panel panel-default aa-checkout-billaddress">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseFour"> Sủ dụng địa chỉ khác </a>
													</h4>
												</div>

												<div id="collapseFour" class="panel-collapse collapse">
													<f:form action="donHang/newInfo.htm" method="post">
														<div class="panel-body">

															<div class="row">
																<div class="col-md-6">
																	<div class="aa-checkout-single-bill">
																		<input id="input-ten" type="text" name="ten"
																			autocomplete="off" placeholder="Họ tên" /> <br>
																		<span class="loi" id="loi-ten"
																			style="font-size: 13px; color: red;"> ㅤ</span>
																	</div>
																</div>
																<div class="col-md-6">
																	<div class="aa-checkout-single-bill">
																		<input id="input-sdt" type="text" name="sdt"
																			autocomplete="off" placeholder="SĐT" /> <br> <span
																			class="loi" id="loi-sdt"
																			style="font-size: 13px; color: red;">ㅤㅤ</span>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<div class="aa-checkout-single-bill">
																		<input id="input-diaChi" type="text" name="diaChi"
																			autocomplete="off" placeholder="Địa chỉ" /> <br>
																		<span class="loi" id="loi-diaChi"
																			style="font-size: 13px; color: red;">ㅤㅤ</span>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-md-12">
																	<button id="btn-newInfo" type="submit"
																		style="background-color: #FF6868; color: white; border: none; padding: 10px">Dùng
																		địa chỉ này</button>
																</div>
															</div>

														</div>
													</f:form>
												</div>

											


											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="checkout-right">
										<h4>Đơn hàng</h4>
										
										<div class="aa-order-summary-area">
											<table class="table table-responsive">
												<thead>
													<tr>
														<th>Sản phẩm</th>
														<th>Size</th>
														<th>Tổng</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="gioHang" items="${gioHangList}">
														<tr>
															<td>${gioHang.sanPham.tenSanPham}<strong> x
																	${gioHang.soLuong}</strong></td>
															<td>${gioHang.sanPham.size}</td>
															<td><c:set var="soTien"
																	value="${gioHang.sanPham.donGia * gioHang.soLuong}" />
																<span style="color: #009432; font-weight: bold">
																	<fmt:formatNumber value="${soTien}" pattern="#,##0" />đ
															</span></td>
														</tr>
														<c:set var="tongTien" value="${tongTien+soTien}" />
													</c:forEach>

												</tbody>
												<tfoot>
													<tr>
														<th>Tổng đơn</th>
														<td></td>
														<td style="color: red; font-weight: bold;"><fmt:formatNumber
																value="${tongTien}" pattern="#,##0" />đ</td>
													</tr>

												</tfoot>
											</table>
										</div>

										<div class="aa-payment-method">

	<form action="donHang/datHang.htm" method="post">
			
			<input style="display:none;" name="tongTien" value="${tongTien}">
				<button id="btn-datHang" name="datHang" class="aa-browse-btn" >Đặt Hàng</button>
			
			</form>
											
										</div>
									</div>
								</div>
							</div>
					
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / Cart view section -->


	<%@ include file="../include/footer.jsp"%>


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
	<script src="<c:url value='assets/js/donHang.js'/>"></script>

</body>
</html>