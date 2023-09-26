<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.servletContext.contextPath}/">
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Sửa sản phẩm</title>
    <link rel="icon" href="assets/img/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href='<c:url value="/assets/css/admin/css/fontawesome.min.css"/>' />   
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href='<c:url value="/assets/css/admin/jquery-ui-datepicker/jquery-ui.min.css" />' type="text/css"  /> 
    <!-- http://api.jqueryui.com/datepicker/ -->
    <link rel="stylesheet" href='<c:url value="/assets/css/admin/css/bootstrap.min.css"/>' />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href='<c:url value="/assets/css/admin/css/templatemo-style.css"/>' />
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
	
 </head>

<body id="reportsPage">
    <div class="" id="home">
        <%@ include file="include/menu.jsp" %> 
    
    <div class="container tm-mt-big tm-mb-big">
      <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
          <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
            <div class="row">
              <div class="col-12">
              <c:if test="${not empty successMessage}">
					    <div class="alert alert-success">${successMessage}</div>
					</c:if>
					<c:if test="${not empty errorMessage}">
					    <div class="alert alert-danger">${errorMessage}</div>
					</c:if>
					
<%-- 					<c:if test="${not empty sizesuccessMessage}">
					    <div class="alert alert-success">${successMessage}</div>
					</c:if>
					<c:if test="${not empty sizeerrorMessage}">
					    <div class="alert alert-danger">${errorMessage}</div>
					</c:if> --%>
                <h2 class="tm-block-title d-inline-block">Sửa sản phẩm</h2>
              </div>
            </div>
            <f:form action="admin/product/edit/${sanPham.maSP}.htm" class="tm-edit-product-form" modelAttribute="product" method="POST" enctype="multipart/form-data">
            <div class="row tm-edit-product-row">            
              <div class="col-xl-6 col-lg-6 col-md-12">                
                
                	<div class="form-group mb-3">
    				    <label for="name">Mã sản phẩm</label>
    				    <%-- <f:input id="masp" path="maSP" type="text" class="form-control validate" required="true" /> --%>
    				    <input id="masp" value="${sanPham.maSP}" type="text" class="form-control validate" readonly required="true" />    				    
    				</div>
    				<div class="form-group mb-3">
    				    <label for="name">Tên sản phẩm</label>
    				    <f:input id="name" path="tenSanPham" type="text" class="form-control validate" required="true" />
    				</div>
    				<div class="form-group mb-3" style="display:none;">
    				    <label for="name">Trạng thái</label>
    				    <f:input id="tt" path="trangThai" value="True" type="text" class="form-control validate" />
    				</div>
    				
    				<div class="form-group mb-3" >
    				    <label for="description">Mô tả</label>
    				    <f:textarea path="moTa" id="moTa" class="form-control validate" rows="3"></f:textarea>
    				</div>
    				 
            </div>
            
            <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">            		
					
			  		<!-- <i class="fas fa-cloud-upload-alt tm-upload-icon"></i> -->
			  		<div class="tm-product-img-dummy mx-auto">
			  		<img style="width:355px; height:240px;" src="${sanPham.hinhAnh.link}">
					    <i onclick="document.getElementById('avatar').click();"></i>
					    <img id="avatar-preview" class="d-none" alt="Ảnh đại diện sản phẩm" style="width: 350px; height: 420px; display: block; margin: 0 auto;">
					  </div>
					  
					  <div class="form-group my-3">
					    <label for="avatar">Đổi hình ảnh đại diện</label>
					    <input type="file" name="avatar" id="avatar" class="form-control-file"  onchange="previewAvatar(event);">
					    <!-- <input type="button" class="btn btn-primary btn-block mx-auto" value="Đổi ảnh" onclick="document.getElementById('avatar').click();" required="true"> -->
					  </div>
					  
					  <div style="margin-top:25px;">
	    				    <label for="s" style="color: #fff;">Các size của sản phẩm:</label>
	    				    
	    				    <div class="size-buttons">
		    				    <c:forEach items="${sizes}" var="size">
									<%-- <c:set var="trimmedMaSP" value="${fn:substring(sanPham.maSP, 0, fn:length(sanPham.maSP) - 2)}" /> --%>
									
									
									<c:set var="indexOfUnderscore" value="${fn:indexOf(sanPham.maSP, '_')}" />
									<c:set var="trimmedMaSP" value="${fn:substring(sanPham.maSP, 0, indexOfUnderscore)}" />
									
									<c:set var="trimmedSize" value="${fn:trim(size)}" />
									
									<button><a style="color: #ff0000;" href="${pageContext.servletContext.contextPath}/admin/product/edit/${trimmedMaSP}_${trimmedSize}.htm">
										${size}
									</a></button>
								</c:forEach>
							</div>
							
							 <form method="POST">
							 <button name="addSize" type="submit" class="btn btn-primary btn-block text-uppercase">Thêm size</button>							 
							 </form>
	    			</div>	  
	    			              
              </div>
           
	           <div class="col-12">
	    			<div class="row">
	    				<div class="form-group mb-3 col-xs-12 col-sm-6">
	    				    <label for="kieuSP">Kiểu: ${sanPham.maKieu.tenKieu}</label>
	    				    <f:select class="custom-select tm-select-accounts" id="kieuSP" path="maKieu.maKieu">
	    				        <c:forEach items="${listkieu}" var="k">
	    							<%-- <option value="${k.maKieu}">${k.tenKieu}</option> --%>
	    							<option value="${k.maKieu}" ${k.maKieu == sanPham.maKieu.maKieu ? 'selected' : ''}>${k.tenKieu}</option>
	  					  		</c:forEach>
	    				    </f:select>
	    				</div>
	    				
	    				<div class="form-group mb-3 col-xs-12 col-sm-6">
	    				    <label for="th">Size: ${sanPham.size}</label>
	    				    <f:input id="size" path="size" type="text" class="form-control validate" required="true" />
	    				</div>
						
						<%-- <div class="form-group mb-3 col-xs-12 col-sm-6">   				    
						    <label for="th">Size: ${sanPham.size}</label>
						    <f:select class="custom-select tm-select-accounts" id="size" path="size">
						        <option value="S" <c:if test="${sanPham.size == 'S'}">selected</c:if>>S</option>
						        <option value="M" <c:if test="${sanPham.size == 'M'}">selected</c:if>>M</option>
						        <option value="L" <c:if test="${sanPham.size == 'L'}">selected</c:if>>L</option>
						        <option value="XL" <c:if test="${sanPham.size == 'XL'}">selected</c:if>>XL</option>
						        <option value="XXL" <c:if test="${sanPham.size == 'XXL'}">selected</c:if>>XXL</option>
						    </f:select>   				    
						</div> --%>
						

	    			</div>
    				
    				<div class="row">
    				    <div class="form-group mb-3 col-xs-12 col-sm-6">
    				        <label for="expire_date">Giá</label>
    				        <f:input id="expire_date" path="donGia" type="number" min="0" class="form-control validate" data-large-mode="true" />
    				    </div>
    				    <div class="form-group mb-3 col-xs-12 col-sm-6">
    				        <label for="stock">Số lượng</label>
    				        <f:input id="stock" path="soLuong" type="number"  min="1" class="form-control validate" required="true" />
    				    </div>
    				</div>    				
				</div>
                                                
          <div class="col-12">
                <button type="submit" class="btn btn-primary btn-block text-uppercase" name="update">Cập nhật sản phẩm</button>
          </div>  
          </div>
          
          </f:form>
        </div>
      </div>
    </div>
    </div>
    </div>


    <script src="<c:url value='js/jquery-3.3.1.min.js'/>"></script>
    <!-- https://jquery.com/download/ -->
    <script src="<c:url value='jquery-ui-datepicker/jquery-ui.min.js'/>"></script>
    <!-- https://jqueryui.com/download/ -->
    <script src="<c:url value='js/bootstrap.min.js'/>"></script>
    <!-- https://getbootstrap.com/ -->
    
    <script src="//cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
    <script>
    	CKEDITOR.replace('moTa');
	</script>
  </body>
</html>

