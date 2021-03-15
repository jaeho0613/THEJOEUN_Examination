package model.dto;

public class KakaoPayReadDTO {
	String tid;
	// String next_redirect_app_url;
	// String next_redirect_mobile_url;
	String next_redirect_pc_url;

	// String android_app_scheme;
	// String ios_app_scheme;
	// String created_at;
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getNext_redirect_pc_url() {
		return next_redirect_pc_url;
	}

	public void setNext_redirect_pc_url(String next_redirect_pc_url) {
		this.next_redirect_pc_url = next_redirect_pc_url;
	}
}
