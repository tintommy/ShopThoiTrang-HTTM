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
    <title>Thêm size</title>
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
                <h2 class="tm-block-title d-inline-block">Thêm size khác</h2>
              </div>
            </div>
            <f:form  class="tm-edit-product-form" modelAttribute="productForm" method="POST" enctype="multipart/form-data">
            <div class="row tm-edit-product-row">            
              <div class="col-xl-6 col-lg-6 col-md-12">                
                
                	<%-- <div class="form-group mb-3">
    				    <label for="name">Mã sản phẩm</label>
    				    <f:input id="masp" path="maSP" type="text" class="form-control validate" required="true" />    				    
    				</div> --%>
    				<div class="form-group mb-3">
    				    <label for="name">Tên sản phẩm</label>
    				    <input id="name" value="${spThemSize.tenSanPham}" type="text" class="form-control validate" readonly required="true" />
    				</div>
    				<div class="form-group mb-3" style="display:none;">
    				    <label for="name">Trạng thái</label>
    				    <input id="tt" value="True" type="text" class="form-control validate" />
    				</div>
    				
    				<div class="form-group mb-3" style="display:none;"	>
    				    <label for="description">Mô tả</label>
    				    <textarea  id="moTa" name="moTa" class="form-control validate" rows="3">${spThemSize.moTa}</textarea>
    				</div>
    				 
            </div>
            
            <%-- <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">            		
					
			  		<div class="tm-product-img-dummy mx-auto">
			  		<img style="width:355px; height:240px;" src="${spThemSize.hinhAnh.link}">
					    <i onclick="document.getElementById('avatar').click();"></i>
					    <img id="avatar-preview" class="d-none" alt="Ảnh đại diện sản phẩm" style="width: 378px; height: 252px; display: block; margin: 0 auto;">
					  </div>		                
              </div> --%>
           
	           <div class="col-12">
	    			<div class="row">
	    				<%-- <div class="form-group mb-3 col-xs-12 col-sm-6">
	    				    <label for="kieuSP">Kiểu sản phẩm</label>
	    				    <f:select class="custom-select tm-select-accounts" id="kieuSP" path="maKieu.maKieu">
	    				        <c:forEach items="${listkieu}" var="k">
	    							<option value="${k.maKieu}">${k.tenKieu}</option>
	  					  		</c:forEach>
	    				    </f:select>
	    				</div> --%>
	    				
	    				<div class="form-group mb-3 col-xs-12 col-sm-6">   				    
	    				    <label for="th">Size cần thêm</label>
	    				    <select class="custom-select tm-select-accounts" id="size" name="sizemoi">
	    							<option>S</option>
	    							<option>M</option>
	    							<option>L</option>
	    							<option>XL</option>
	    							<option>XXL</option>
	    				    </select>  		  
	    				    		    
	    				</div>
	    			</div>
    				
    				<div class="row">
    				    <div class="form-group mb-3 col-xs-12 col-sm-6">
    				        <label for="expire_date">Giá</label>
    				        <input id="expire_date" name="gia" type="number" value="1000" min="0" class="form-control validate" data-large-mode="true" />
    				    </div>
    				    <div class="form-group mb-3 col-xs-12 col-sm-6">
    				        <label for="stock">Số lượng</label>
    				        <input id="stock" name="soluong" type="number"  value="1" min="1" class="form-control validate" required="true" />
    				    </div>
    				</div>    				
				</div>
                                                
          <div class="col-12">
                <button type="submit" class="btn btn-primary btn-block text-uppercase" name="add">Thêm</button>
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

