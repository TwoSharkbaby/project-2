package com.twosharkbaby.www.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.MovieReview;
import com.twosharkbaby.www.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	// 등록 가능한 seq 값을 찾는 명령(JPQL)
	// - 동일 그룹, 하단 게시글 중 차수가 같거나 작은 글 중 가장 작은 seq를 구한다
	// - 없을 경우 null이므로 반드시 Long 형태로 처리(혹은 nvl 처리가 필요)
	@Query(value = "select min(r.seq) from Reply r where r.grp = :#{#origin.grp} and r.seq > :#{#origin.seq} and r.dep <= :#{#origin.dep}", nativeQuery = true)
	Long getAvailableSeq(@Param("origin")Reply origin);

	Long countByGrp(Long grp);
	
	Reply findById(Long id);

	@Modifying
	@Transactional
	@Query("update Reply r set r.seq = r.seq + 1 where r.grp = :#{#reply.grp} and r.seq >= :#{#reply.seq}")
	void increaseSequence(@Param("reply") Reply reply);

	@Query("select count(*) from Reply r where r.board.id = :boardId")
	long findByCount(@Param("boardId") Long boardId);

	Page<Reply> findByBoardId(Long id, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("update Reply r set r.content = :#{#reply.content} where r.id = :#{#reply.id}")
	Integer update(@Param("reply")Reply reply);
	
	Long deleteById(Long id);
	
	Long deleteByBoardId(Long id);
	
	@Modifying
	@Query("update Reply set good = (good)+1 where id = :id")
	void upGood(@Param("id")Long id); 
	
	@Modifying
	@Query("update Reply set good = (good)-1 where id = :id")
	void downGood(@Param("id")Long id); 
	
	@Modifying
	@Query("update Reply set bad = (bad)+1 where id = :id")
	void upBad(@Param("id")Long id); 
	
	@Modifying
	@Query("update Reply set bad = (bad)-1 where id = :id")
	void downBad(@Param("id")Long id); 
	
	List<Reply> findByBoardId(Long id);

}
