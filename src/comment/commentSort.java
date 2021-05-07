package comment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class commentSort {
	
	//RECURSION version O(nlogn) 최종
	public static void recursivePreorder(CommentDTO comment, ArrayList<CommentDTO> sorted_List) throws Exception {
		
		sorted_List.add(comment);

		if (!comment.commentChildList.isEmpty()) {
			for (int i = 0; i < comment.commentChildList.size(); i++)
				recursivePreorder(comment.commentChildList.get(i), sorted_List);
		}
	}

	//STACK version O(n^2) remove때문에ㅠㅠ
	public static ArrayList<CommentDTO> preorder(ArrayList<CommentDTO> commentList) {
		// commentList 는 commentID순으로 ASC 되어 있음

		if (commentList.isEmpty()) {
			return null; // 댓글 없음
		}

		ArrayList<CommentDTO> sorted_list = new ArrayList<CommentDTO>();

		Stack<CommentDTO> s = new Stack<>();

		s.push(commentList.get(0));

		do {
			CommentDTO TOP = s.lastElement();
			System.out.println("===================top의 아이디 " + TOP.getCommentID());
			if (!TOP.commentChildList.isEmpty()) {
				int childListSize = TOP.commentChildList.size();
				for (int i = childListSize - 1; i >= 0; i--)
					s.push(TOP.commentChildList.get(i));
			} else {
				CommentDTO POP = s.pop();

				// new
				if (POP.getCommentID() != 0) { // root(bbs)가 아니면
					sorted_list.add(POP);
					commentList.get(POP.getParentCommentID()).commentChildList.remove(POP);
				}

				// 기존 코드
//				sorted_list.add(POP);
//				TOP.commentChildList.remove(POP);
			}

		} while (!s.empty());

		return sorted_list;
	}

	public static void main(String[] args) throws Exception {

		// 1. 댓글 불러오기
		ArrayList<CommentDTO> commentList = new ArrayList<>(); // DB에서 가져온 comment list commentID별로 정렬 되어 있음

		// ---------실행 예 나중에 지울 것
		CommentDTO c0 = new CommentDTO();
		c0.setCommentID(0);
		c0.setParentCommentID(-1);
		commentList.add(c0);

		CommentDTO c1 = new CommentDTO();
		c1.setCommentID(1);
		c1.setParentCommentID(0);
		commentList.add(c1);

		CommentDTO c2 = new CommentDTO();
		c2.setCommentID(2);
		c2.setParentCommentID(1);
		commentList.add(c2);

		CommentDTO c3 = new CommentDTO();
		c3.setCommentID(3);
		c3.setParentCommentID(1);
		commentList.add(c3);

		CommentDTO c4 = new CommentDTO();
		c4.setCommentID(4);
		c4.setParentCommentID(1);
		commentList.add(c4);

		CommentDTO c5 = new CommentDTO();
		c5.setCommentID(5);
		c5.setParentCommentID(3);
		commentList.add(c5);

		CommentDTO c6 = new CommentDTO();
		c6.setCommentID(6);
		c6.setParentCommentID(0);
		commentList.add(c6);

		CommentDTO c7 = new CommentDTO();
		c7.setCommentID(7);
		c7.setParentCommentID(6);
		commentList.add(c7);

		CommentDTO c8 = new CommentDTO();
		c8.setCommentID(8);
		c8.setParentCommentID(7);
		commentList.add(c8);

		CommentDTO c9 = new CommentDTO();
		c9.setCommentID(9);
		c9.setParentCommentID(8);
		commentList.add(c9);

		CommentDTO c10 = new CommentDTO();
		c10.setCommentID(10);
		c10.setParentCommentID(8);
		commentList.add(c10);

		CommentDTO c11 = new CommentDTO();
		c11.setCommentID(11);
		c11.setParentCommentID(7);
		commentList.add(c11);

		CommentDTO c12 = new CommentDTO();
		c12.setCommentID(12);
		c12.setParentCommentID(9);
		commentList.add(c12);

		CommentDTO c13 = new CommentDTO();
		c13.setCommentID(13);
		c13.setParentCommentID(5);
		commentList.add(c13);

		CommentDTO c14 = new CommentDTO();
		c14.setCommentID(14);
		c14.setParentCommentID(0);
		commentList.add(c14);

		CommentDTO c15 = new CommentDTO();
		c15.setCommentID(15);
		c15.setParentCommentID(14);
		commentList.add(c15);

		CommentDTO c16 = new CommentDTO();
		c16.setCommentID(16);
		c16.setParentCommentID(14);
		commentList.add(c16);

		CommentDTO c17 = new CommentDTO();
		c17.setCommentID(17);
		c17.setParentCommentID(16);
		commentList.add(c17);

		CommentDTO c18 = new CommentDTO();
		c18.setCommentID(18);
		c18.setParentCommentID(17);
		commentList.add(c18);

		CommentDTO c19 = new CommentDTO();
		c19.setCommentID(19);
		c19.setParentCommentID(17);
		commentList.add(c19);
		// --------------------

		// 2. 트리구조 만들기
//		for (int i = 1; i < commentList.size(); i++) {
//			commentList.get(commentList.get(i).getParentCommentID()).commentChildList.add(commentList.get(i));
//		}

		// 2. 트리구조 만들기 ( 향상된 for문 ver)
		for (CommentDTO comment : commentList) {
			if (comment.getCommentID() == 0)
				continue;
			commentList.get(comment.getParentCommentID()).commentChildList.add(comment);
		}

		// 중간출력 (tree 구조 확인하기)
//		for (int i = 0; i < commentList.size(); i++) {
//			System.out.println("현채 차일드 리스트 사이즈 : " + commentList.get(i).commentChildList.size());
//			System.out.print("index : "+i+", commentID : "+ commentList.get(i).getCommentID()+"  ||");
//			for (int j = 0; j < commentList.get(i).commentChildList.size(); j++) {
//				System.out.print(commentList.get(i).commentChildList.get(j).getCommentID()+" ");
//			}
//			System.out.println();
//		}

		// 3 preorder 순회
		ArrayList<CommentDTO> sorted_List = new ArrayList<CommentDTO>();
		recursivePreorder(commentList.get(0), sorted_List);

		// 출력
		for (int i = 0; i < sorted_List.size(); i++) {
			System.out.println(sorted_List.get(i).getCommentID());
		}

	}

}
