package ptithcm.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SANPHAM")
public class SanPhamEntity {
	@Id
	@Column(name = "MASP")
	private String maSP;
	@Column(name = "TENSANPHAM")
	private String tenSanPham;
	@Column(name = "SIZE")
	private String size;
	@Column(name = "MOTA")
	private String moTa;
	@Column(name = "SOLUONG")
	private Integer soLuong;
	@Column(name = "DONGIA")
	private int donGia;
	@Column(name = "SOSAOTB")
	private float soSaoTB;
	@Column(name = "TRANGTHAI")
	private boolean trangThai;

	@ManyToOne
	@JoinColumn(name = "MAKIEU")
	private KieuSanPhamEntity maKieu;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name = "NGAYTHEM")
	private Date ngayThem;

	@ManyToOne
	@JoinColumn(name = "MAHINHANH")
	private HinhAnhEntity hinhAnh;

	@OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
	private List<GioHangEntity> gioHangs;

	@OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
	private List<DanhGiaEntity> danhGiaList;

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public float getSoSaoTB() {
		return soSaoTB;
	}

	public void setSoSaoTB(float soSaoTB) {
		this.soSaoTB = soSaoTB;
	}

	public List<GioHangEntity> getGioHangs() {
		return gioHangs;
	}

	public void setGioHangs(List<GioHangEntity> gioHangs) {
		this.gioHangs = gioHangs;
	}

	public List<DanhGiaEntity> getDanhGiaList() {
		return danhGiaList;
	}

	public void setDanhGiaList(List<DanhGiaEntity> danhGiaList) {
		this.danhGiaList = danhGiaList;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayThem() {
		return ngayThem;
	}

	public void setNgayThem(Date ngayThem) {
		this.ngayThem = ngayThem;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public HinhAnhEntity getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(HinhAnhEntity hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public KieuSanPhamEntity getMaKieu() {
		return maKieu;
	}

	public void setMaKieu(KieuSanPhamEntity maKieu) {
		this.maKieu = maKieu;
	}

}