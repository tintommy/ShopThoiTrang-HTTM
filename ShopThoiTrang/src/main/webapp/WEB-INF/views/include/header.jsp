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
									<li><a href="lich-su-mua-hang.htm">Lịch sử mua</a></li>
									<li><a href="userInfo.htm">Tài khoản</a></li>

								
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
						
						<div style="display: flex; justify-content: space-between; align-items: center;">
						    <div class="aa-search-box">
						        <a href="${pageContext.servletContext.contextPath}/imageSearch.htm" class="search-button">
						            <button style="width: 50px; height: 40px; margin-top: -20px; margin-right: -40px;" name="btnImageSearch">
						                <span class="fa fa-picture-o"></span>
						            </button>
						        </a>
						    </div>
						
						    <!-- search box -->
						    <div class="aa-search-box">
						        <form action="shop.htm" method="post">
						            <input type="text" name="key" id="search-barr" placeholder="Bạn tìm gì">
						            <button type="submit" name="btnSearch">
						                <span class="fa fa-search"></span>
						            </button>
						        </form>
						    </div>
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
						<li><a href=".htm">Home</a></li>
						<li></li>
						<li><a href="shop.htm">Shop</a></li>
					<c:if test="${not empty sessionScope.USER}">
						<li><a href="recommend.htm">Gợi ý hôm nay</a></li>
					</c:if>
						<!-- <li><a href="#">Nam <span class="caret"></span></a>
							<ul class="dropdown-menu">

								<li><a href="shop.htm">Áo nam</a></li>
								<li><a href="shop.htm">Quần nam</a></li>
							
							</ul>
						</li>
						<li><a href="#">Nữ <span class="caret"></span></a>
							<ul class="dropdown-menu">

								<li><a href="shop.htm">Áo nữ</a></li>
								<li><a href="shop.htm">Quần nữ</a></li>
								
							</ul>
						</li> -->
							
								<%-- <li><a href="#">Nam <span class="caret"></span></a>
							<ul class="dropdown-menu">

								<c:forEach var="loai" items="${loaiSPNam}" begin="0">
									<li><a href="shop/${loai.maLoai}.htm">${loai.tenLoai}</a></li>
								</c:forEach>



							</ul></li>
						<li><a href="#">Nữ <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<c:forEach var="loai" items="${loaiSPNu}" begin="0">
									<li><a href="shop/${loai.maLoai}.htm">${loai.tenLoai}</a></li>
								</c:forEach>

							</ul></li> --%>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
</section>
