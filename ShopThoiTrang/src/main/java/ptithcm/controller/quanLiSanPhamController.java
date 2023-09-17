package ptithcm.controller;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.entity.HinhAnhEntity;
import ptithcm.entity.KieuSanPhamEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.KieuSanPhamService;
import ptithcm.service.SanPhamService;
import ptithcm.service.loaiSanPhamService;

@Transactional
@Controller
public class quanLiSanPhamController {
	
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	KieuSanPhamService kieuService;
	@Autowired
	loaiSanPhamService loaiService;
	
//	@Autowired
//	ServletContext context;
	String filePath = "C:\\Users\\vanlu\\Documents\\shopThoiTrang\\src\\main\\webapp\\assets\\img\\product\\"; // Đường dẫn tới thư mục lưu trữ tệp tin hình ảnh
	String imgXoaPath="C:\\Users\\vanlu\\Documents\\shopThoiTrang\\src\\main\\webapp\\"; // để xóa hình
	
	@RequestMapping(value="admin/product", method = RequestMethod.GET)
	public String product(ModelMap model, HttpServletRequest request) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
		
		List<SanPhamEntity> listAllSanPham = sanPhamService.layAllSanPham();
		model.addAttribute("listAllSanPham",listAllSanPham);
		
		List<SanPhamEntity> listSanPhamNgungBan = sanPhamService.layAllSanPhamDaNgungBan();
		model.addAttribute("listNgungBan",listSanPhamNgungBan);
		
		List<KieuSanPhamEntity> listkieu=kieuService.layKieu();
		model.addAttribute("listkieu", listkieu);
		
		List<LoaiSanPhamEntity> listLoai = loaiService.layLoai();
		model.addAttribute("listLoai", listLoai);
		
		return "admin/product";
	}
	
	
	@RequestMapping(value="admin/product", params="changeStatus", method = RequestMethod.POST)
	public String updateTrangThaiSanPham(HttpServletRequest request) {
		String maSp= request.getParameter("maSPSuaTT");
		boolean trangThai = Boolean.parseBoolean(request.getParameter("trangThaiSp"));
	    SanPhamEntity sanPham = sanPhamService.laySanPham(maSp);
	    sanPham.setTrangThai(!trangThai); // set trạng thái sản phẩm từ "đang bán" sang "ngừng bán" hoặc ngược lại
	    sanPhamService.updateSanPham(sanPham);

	    return "redirect:/admin/product.htm";
	}
	
	
	@RequestMapping(value = "/admin/product/add", method = RequestMethod.GET)
	public String viewAddProduct(ModelMap model, HttpServletRequest request) {
		List<KieuSanPhamEntity> listkieu=kieuService.layKieu();
		model.addAttribute("listkieu", listkieu);
	    model.addAttribute("productForm", new SanPhamEntity());
	    return "admin/addProduct";
	}
	
	@RequestMapping(value = "/admin/product/add", params = "add", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("productForm") SanPhamEntity product, 
	        @RequestParam("avatar") MultipartFile avatar, 
	        ModelMap model) throws IOException {

	    //Lấy ngày tháng cộng vào tên file để khỏi bị trùng file
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = formatter.format(today);
		
		HinhAnhEntity hinhAnh = new HinhAnhEntity();
		
	    String avatarFileName = now +"-"+ avatar.getOriginalFilename();
//	    String avatarFilePath = context.getRealPath("/assets/img/sanPham/" + avatarFileName);
	    String avatarFilePath = filePath + avatarFileName;
	    File avatarFile = new File(avatarFilePath);
	    avatar.transferTo(avatarFile);
	    hinhAnh.setLink("assets/img/product/"+avatarFileName);
	    
        sanPhamService.themHinhAnhSanPham(hinhAnh);
	    product.setHinhAnh(hinhAnh);
		product.setNgayThem(today);
		
		try {
			// Lấy mã sản phẩm đã điền từ product
	        String maSPDaDien = product.getMaSP();
	        String size = product.getSize();
	        String newMaSP = maSPDaDien + "_" + size; // Tạo mã sản phẩm mới
	        product.setMaSP(newMaSP); // Cập nhật mã sản phẩm trong product

	        sanPhamService.themSanPham(product);
	        model.addAttribute("successMessage", "Thêm sản phẩm thành công.");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Có lỗi xảy ra khi thêm sản phẩm. " + e.getMessage());
	        return "/admin/addProduct";
	    }
		
	    return "admin/addProduct";
	}
	
	@RequestMapping(value = "/admin/product/edit/{masp}", method = RequestMethod.GET)
	public String viewEditProduct(@PathVariable("masp") String masp, ModelMap model, HttpServletRequest request) {		
	    SanPhamEntity sanPham = sanPhamService.laySanPham(masp);
	    List<KieuSanPhamEntity> listkieu = kieuService.layKieu();
	    
	    List<String> sizes = sanPhamService.laySizeTheoTenSanPham(masp);
		model.addAttribute("sizes", sizes);
		
	    model.addAttribute("sanPham", sanPham);
	    model.addAttribute("listkieu", listkieu);
	    model.addAttribute("product", sanPham);
	    return "admin/editProduct";
	}
	
	
	@RequestMapping(value = "/admin/product/edit/{masp}", params = "update", method = RequestMethod.POST)
	public String editProduct(@PathVariable("masp") String masp,
	                           @ModelAttribute("product") SanPhamEntity product,
	                           @RequestParam("avatar") MultipartFile avatar,
	                           ModelMap model) throws IOException {
		SanPhamEntity sp= sanPhamService.laySanPham(masp);
		
	    //Lấy ngày tháng cộng vào tên file để khỏi bị trùng file
	    Date today = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    String now = formatter.format(today);
	    
	    if (!avatar.isEmpty()) {
	        
	        
	        // Xóa hình đại diện cũ
	        HinhAnhEntity hinhAnhCu = sp.getHinhAnh();
	        if (hinhAnhCu != null) {
	            xoaTepTinHinhAnh(hinhAnhCu.getLink());
	        }
	        
	        
	        String avatarFileName = now + "-" + avatar.getOriginalFilename();
	        String avatarFilePath = filePath + avatarFileName;
	        File avatarFile = new File(avatarFilePath);
	        avatar.transferTo(avatarFile);
	        HinhAnhEntity hinhAnhMoi = new HinhAnhEntity();
	        hinhAnhMoi.setLink("assets/img/product/"+avatarFileName);
	        product.setHinhAnh(hinhAnhMoi); 
	    }  else {
	        // Giữ nguyên ảnh đại diện cũ
	        product.setHinhAnh(sp.getHinhAnh());
	    } 
	    
	    product.setNgayThem(sp.getNgayThem());

	    try {
	        sanPhamService.updateSanPham(product);
	        model.addAttribute("successMessage", "Cập nhật sản phẩm thành công.");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật sản phẩm. " + e.getMessage());
	        return "admin/editProduct";
	    }
	    return "admin/editProduct";
	}
	
//	@RequestMapping(value = "/admin/product/edit/{masp}", params = "addSize", method = RequestMethod.POST)
//	public String showAddNewProductForm(@PathVariable("masp") String masp, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
//	    SanPhamEntity sp= sanPhamService.laySanPham(masp);
//	    // Sử dụng Flash Attributes để giữ lại thông tin sản phẩm
//	    redirectAttributes.addFlashAttribute("spThemSize", sp);
//
//	    return "redirect:/admin/product/addSize.htm";
//	}		
//	
//	@RequestMapping(value = "/admin/product/addSize", method = RequestMethod.GET)
//    public String viewAddNewSizeProductForm(ModelMap model,  HttpServletRequest request, RedirectAttributes redirectAttributes,
//    		@ModelAttribute("spThemSize") SanPhamEntity sp) {
//		SanPhamEntity spmoi =new SanPhamEntity();
//		spmoi=sp;
//        return "admin/addSize";
//    }
//	
//	
//	@RequestMapping(value = "/admin/product/addSize", params = "add", method = RequestMethod.POST)
//    public String AddNewSizeProductForm(@ModelAttribute("productForm") SanPhamEntity product, ModelMap model,  HttpServletRequest request, RedirectAttributes redirectAttributes,
//    		@RequestParam("sizemoi") String sizemoi, @RequestParam("gia") int gia, @RequestParam("soluong") int soluong,
//    		@ModelAttribute("spThemSize") SanPhamEntity sp) {
//
//		product= sp;
//		product.setSize(sizemoi);
//		product.setMaSP(sp.getMaSP()+"_"+sizemoi);
//		product.setSoLuong(soluong);
//		product.setDonGia(gia);
//		Date today = new Date();
//		product.setNgayThem(today);
//		try {
//	        sanPhamService.themSanPham(product);
//	        model.addAttribute("successMessage", "Thêm thành công.");
//	    } catch (Exception e) {
//	        model.addAttribute("errorMessage", "Có lỗi xảy ra khi thêm" + e.getMessage());
//	        return "/admin/addSize";
//	    }
//		
//	    return "admin/addSize";
//    }
	
	@RequestMapping(value = "/admin/product/edit/{masp}", params = "addSize", method = RequestMethod.POST)
	public String showAddNewProductForm(@PathVariable("masp") String masp, ModelMap model, HttpServletRequest request) {
	    SanPhamEntity sp= sanPhamService.laySanPham(masp);
	    HttpSession session = request.getSession();
	    session.setAttribute("spThemSize", sp);
	    
	    List<String> sizes = sanPhamService.laySizeTheoTenSanPham(masp);
		model.addAttribute("sizes", sizes);

	    return "redirect:/admin/product/addSize.htm";
	}		
	
	@RequestMapping(value = "/admin/product/addSize", method = RequestMethod.GET)
    public String viewAddNewSizeProductForm(ModelMap model,  HttpServletRequest request) {
		HttpSession session = request.getSession();
	    SanPhamEntity spThemSize= (SanPhamEntity) session.getAttribute("spThemSize");
        return "admin/addSize";
    }
	
	
	@RequestMapping(value = "/admin/product/addSize", params = "add", method = RequestMethod.POST)
    public String AddNewSizeProductForm(@ModelAttribute("productForm") SanPhamEntity product, ModelMap model,  HttpServletRequest request,
    		@RequestParam("sizemoi") String sizemoi, @RequestParam("gia") int gia, @RequestParam("soluong") int soluong) {
		
		HttpSession session = request.getSession();
	    SanPhamEntity spThemSize= (SanPhamEntity) session.getAttribute("spThemSize");

		product= spThemSize;
		String maSP = spThemSize.getMaSP();
		int lastIndex = maSP.lastIndexOf("_"); // Tìm vị trí cuối cùng của dấu gạch
		String maSPMoi = maSP.substring(0, lastIndex);
		product.setSize(sizemoi);
		product.setMaSP(maSPMoi+"_"+sizemoi);
		product.setSoLuong(soluong);
		product.setDonGia(gia);
		Date today = new Date();
		product.setNgayThem(today);
		try {
	        sanPhamService.themSanPham(product);
	        model.addAttribute("successMessage", "Thêm thành công.");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Có lỗi xảy ra khi thêm" + e.getMessage());
	        return "/admin/addSize";
	    }
		
//		session.removeAttribute("spThemSize");
//		return "redirect:/admin/product/addSize.htm";
	    return "admin/addSize";
    }

//	@RequestMapping(value = "/admin/product/edit/{masp}", params = "update", method = RequestMethod.POST)
//	public String editProduct(@PathVariable("masp") String masp,
//	                           @ModelAttribute("product") SanPhamEntity product,
//	                           @RequestParam("avatar") MultipartFile avatar,
//	                           @RequestParam("images") MultipartFile[] images,
//	                           @RequestParam("thongSo") MultipartFile thongSo,
//	                           ModelMap model) throws IOException {
//		SanPhamEntity sp= sanPhamService.laySanPham(masp);
//		
//	    //Lấy ngày tháng cộng vào tên file để khỏi bị trùng file
//	    Date today = new Date();
//	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//	    String now = formatter.format(today);
//	    
//	    if (!avatar.isEmpty()) {
//	        
//	        
//	        // Xóa hình đại diện cũ
//	        String hinhAnhDaiDien = sp.getHinhAnhDaiDien();
//	        if (hinhAnhDaiDien != null) {
//	            xoaTepTinHinhAnh(hinhAnhDaiDien);
//	        }
//	        
//	        String avatarFileName = now + "-" + avatar.getOriginalFilename();
//	        String avatarFilePath = filePath + avatarFileName;
//	        File avatarFile = new File(avatarFilePath);
//	        avatar.transferTo(avatarFile);
//	        product.setHinhAnhDaiDien("assets/img/sanPham/" + avatarFileName); 
//	    }  else {
//	        // Giữ nguyên ảnh đại diện cũ
//	        product.setHinhAnhDaiDien(sp.getHinhAnhDaiDien());
//	    } 
//		
//		// Kiểm tra xem có thay đổi hình ảnh khác hay không
//		if (images.length > 0 && !images[0].isEmpty()) {			 
//	        
//	        
//	        // Xóa các hình ảnh khác cũ
//	        List<HinhAnhEntity> danhSachHinhAnh = sp.getHinhAnhs();
//	        if (danhSachHinhAnh != null && !danhSachHinhAnh.isEmpty()) {
//	            for (HinhAnhEntity hinhAnh : danhSachHinhAnh) {
//	                String tenTep = hinhAnh.getLink();
//	                xoaTepTinHinhAnh(tenTep);	                
//	            }
//	            sanPhamService.xoaHinhAnhSanPham(danhSachHinhAnh); //xóa trong csdl
//	        }
//	        
//	        List<HinhAnhEntity> hinhAnhKhacs = new ArrayList<>();
//	        for (MultipartFile file : images) {
//			    String fileName = now + file.getOriginalFilename();
//			    String otherImgfilePath = filePath + fileName;
//			    File dest = new File(otherImgfilePath);
//			    file.transferTo(dest);
//		
//			    HinhAnhEntity hinhAnhKhac = new HinhAnhEntity();
//			    hinhAnhKhac.setLink("assets/img/sanPham/"+fileName);
//			    hinhAnhKhac.setSanPham(product);
//			    hinhAnhKhacs.add(hinhAnhKhac);
//	        }
//	        product.setHinhAnhs(hinhAnhKhacs);
//	        sanPhamService.updateSanPham(product);
//	        sanPhamService.themHinhAnhSanPham(hinhAnhKhacs);
//		}
//	    
//		
//	    if (!thongSo.isEmpty()) {  
//	        
//	        
//	        // Xóa hình tskt cũ
//	        String anhThongSoKT = sp.getThongSoKt();
//	        if (anhThongSoKT != null) {
//	            xoaTepTinHinhAnh(anhThongSoKT);
//	        }
//	        
//	        String thongSoFileName = now + "-" + thongSo.getOriginalFilename();
//	        String thongSoFilePath = filePath + thongSoFileName;
//	        File thongSoFile = new File(thongSoFilePath);
//	        thongSo.transferTo(thongSoFile);
//	        product.setThongSoKt("assets/img/sanPham/" + thongSoFileName);
//	    } else {
//	        // Giữ nguyên ảnh đại diện cũ
//	        product.setThongSoKt(sp.getThongSoKt());
//	    }
//	    
//	    product.setNgayThem(sp.getNgayThem());
//
//	    try {
//	        sanPhamService.updateSanPham(product);
////	        sanPhamService.suaHinhAnhSanPham(hinhAnhKhacs);
//	        model.addAttribute("successMessage", "Cập nhật sản phẩm thành công.");
//	    } catch (Exception e) {
//	        model.addAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật sản phẩm. " + e.getMessage());
//	        return "admin/editProduct";
//	    }
//	    return "admin/editProduct";
//	}
	
	public void xoaTepTinHinhAnh(String tenTep) {
	    String imgPath = imgXoaPath + tenTep; // Đường dẫn tới thư mục chứa hình ảnh
	    File tepTin = new File(imgPath);
	    
	    if (tepTin.exists()) {
	        tepTin.delete(); // Xóa tệp tin hình ảnh từ server
	    }
	}

	@RequestMapping(value="admin/product", params="deleteSP", method = RequestMethod.POST)
	public String deleteSanPham(HttpServletRequest request, ModelMap model) {
	    String maSp = request.getParameter("maSPXoa");
	    SanPhamEntity sanPham = sanPhamService.laySanPham(maSp);
	    
	    boolean coGioHangLienKet = sanPhamService.kiemTraSanPhamCoNamTrongGioHang(maSp);
	    boolean coDonHangLienKet = sanPhamService.kiemTraSanPhamCoNamTrongDonHang(maSp);
	    
	    if (coGioHangLienKet) {
	        model.addAttribute("errorMessageXoaSP", "Không thể xóa sản phẩm vì đang nằm trong giỏ hàng!");
	        return "admin/product";
	    }	    
	    else if (coDonHangLienKet) {
	        model.addAttribute("errorMessageXoaSP", "Không thể xóa sản phẩm vì đang nằm đơn hàng!");
	        return "admin/product";
	    }
	    else {
	        
	        
	    	// Xóa sản phẩm trong cơ sở dữ liệu
	        sanPhamService.xoaSanPham(sanPham);
	        
	        
//	        // Xóa hình sản phẩm trong cơ sở dữ liệu	        
//	        sanPhamService.xoaHinhAnhSanPham(sanPham.getHinhAnh());
//	        // Xóa hình đại diện
//	        String hinhAnhDaiDien = sanPham.getHinhAnh().getLink();
//	        if (hinhAnhDaiDien != null) {
//	            xoaTepTinHinhAnh(hinhAnhDaiDien);
//	        }
	        
	    }
	    
	    return "redirect:/admin/product.htm";
	}

	
	@RequestMapping(value = "admin/product", params = "deleteKieu", method = RequestMethod.POST)
	public String deleteKieu(HttpServletRequest request, ModelMap model) {
	    int maKieu = Integer.parseInt(request.getParameter("maKieuXoa"));
	    KieuSanPhamEntity kieu = kieuService.layKieuTheoMa(maKieu);
	    
	    boolean coSanPhamLienKet = kieuService.kiemTraSanPhamTheoKieu(maKieu);
	    
	    if (coSanPhamLienKet) {
	        model.addAttribute("errorMessageKieu", "Không thể xóa kiểu vì có sản phẩm đang liên kết!");
	        return "admin/product";
	    } else 
	        kieuService.xoaKieu(kieu);
	    
	    return "redirect:/admin/product.htm";
	}
	
	@RequestMapping(value = "admin/product", params = "deleteLoai", method = RequestMethod.POST)
	public String deleteLoai(HttpServletRequest request, ModelMap model) {
	    String maLoai = request.getParameter("maLoaiXoa");
	    LoaiSanPhamEntity loai = loaiService.layLoaiTheoMa(maLoai);
	    
	    boolean coKieuLienKet = loaiService.kiemTraLoai(maLoai);
	    
	    if (coKieuLienKet) {
	        model.addAttribute("errorMessageLoai", "Không thể xóa loại vì có kiểu đang liên kết!");
	        return "admin/product";
	    } else 
	        loaiService.xoaLoai(loai);
	    
	    return "redirect:/admin/product.htm";
	}
	
	
}
