package ptithcm.designpattern.FacadePattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.service.DonHangService;
import ptithcm.service.SanPhamService;
import ptithcm.service.nguoiDungService;

@Component
@Transactional
public class ThongKeFacade {
	@Autowired
	SanPhamService sanPhamService;

	@Autowired
	DonHangService donHangService;

	@Autowired
	nguoiDungService nguoiDungService;
}
