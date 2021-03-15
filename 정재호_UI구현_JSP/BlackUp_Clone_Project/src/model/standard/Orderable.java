package model.standard;

import model.dto.OrdersDTO;

public interface Orderable {
	
	// 주문 정보 저장
	int insert(OrdersDTO order);
}
