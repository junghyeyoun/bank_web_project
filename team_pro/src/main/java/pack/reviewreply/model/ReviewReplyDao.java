package pack.reviewreply.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.review.controller.ReviewBean;
import pack.reviewreply.controller.ReviewReplyBean;

@Repository
public class ReviewReplyDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReviewReplyMappingInterface replyMappingInterface;
	
	// 댓글 등록
	public boolean replyinsert(ReviewReplyBean bean) {
		boolean b = false;
		int re = replyMappingInterface.insertreply(bean);
		if(re>0) b = true;
		return b;
	}
	
	// 댓글 조회
	public List<ReviewReplyDto> selectAll(int reviewno) {
		List<ReviewReplyDto> list = replyMappingInterface.selectreply(reviewno);
		logger.info("읽은 댓글 수 : " + list.size());
		return list;
	}
	
	// 댓글 수정 전 조회
	public ReviewReplyDto replydetail(int replyid) {
		ReviewReplyDto dto = replyMappingInterface.selectOne(replyid);
		return dto;
	}
	
	// 댓글 수정
	public boolean update(ReviewReplyBean bean) {
		boolean b = false;
		int re = replyMappingInterface.updatereply(bean); 
		if (re > 0)
			b = true;
		return b;
	}
	
	// 댓글 삭제
	public boolean delete(int replyid) {
		boolean b = false;
		int re = replyMappingInterface.deletereply(replyid);
		if (re > 0)
			b = true;
		return b;
	}
}
