<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<header id="aa-header">
	<!-- start header top  -->
	<div class="aa-header-top">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-header-top-area">
						<!-- start header top left -->
						<div class="aa-header-top-left"></div>
						<!-- / header top left -->
						<div class="aa-header-top-right">
							<ul class="aa-head-top-nav-right">

								<!-- <li class="hidden-xs"><a href="checkout.html">Checkout</a></li> -->


								<c:if test="${not empty sessionScope.USER}">
									<li><a href="yeuThich.htm">Yêu thích</a></li>
									<li><a href="user/logout.htm">Lịch sử mua hàng</a></li>
									<li><a href="userInfo.htm">Tài khoản của tôi</a></li>

								
									<li><a href="user/logout.htm">Đăng xuất</a></li>
									
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- / header top  -->

	<!-- start header bottom  -->
	<div class="aa-header-bottom">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-header-bottom-area">
						<!-- logo  -->
						<div class="aa-logo">
							<!-- Text based logo -->
							<a href=".htm"> <span class="fa fa-shopping-cart"></span>
								<p>
									daily<strong>Shop</strong> <span>Your Shopping Partner</span>
								</p>
							</a>
							<!-- img based logo -->
							<!-- <a href="index.html"><img src="img/logo.jpg" alt="logo img"></a> -->
						</div>
						<!-- / logo  -->
						<!-- cart box -->
						<div class="aa-cartbox">
							<c:if test="${empty sessionScope.USER}">


								<a class="aa-cart-link" href="user/login.htm"> <span
									class="fa fa-user"></span> <span class="aa-cart-title">ĐĂNG
										NHẬP</span>
								</a>
							</c:if>

							<c:if test="${not empty sessionScope.USER}">

								<a class="aa-cart-link" href="gioHang.htm"> <span
									class="fa fa-shopping-basket"></span> <span
									class="aa-cart-title">SHOPPING CART</span>

								</a>
							</c:if>

						</div>
						<!-- / cart box -->
						<!-- search box -->
						<div class="aa-search-box">
							<form action="">
								<input type="text" name="" id=""
									placeholder="Search here ex. 'man' ">
								<button type="submit">
									<span class="fa fa-search"></span>
								</button>
							</form>
						</div>
						<!-- / search box -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- / header bottom  -->
</header>
<!-- / header section -->

<section id="menu">
	<div class="container">
		<div class="menu-area">
			<!-- Navbar -->
			<div class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse">
					<!-- Left nav -->
					<ul class="nav navbar-nav">
						<li><a href=".html">Home</a></li>
						<li><a href="#">Nam <span class="caret"></span></a>
							<ul class="dropdown-menu">

								<c:forEach var="loai" items="${loaiSPNam}" begin="0">
									<li><a href="#">${loai.tenLoai}</a></li>
								</c:forEach>



							</ul></li>
						<li><a href="#">Nữ <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<c:forEach var="loai" items="${loaiSPNu}" begin="0">
									<li><a href="#">${loai.tenLoai}</a></li>
								</c:forEach>

							</ul></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
</section>
