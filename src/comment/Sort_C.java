package comment;

import java.util.ArrayList;

public class Sort_C {
	
	ArrayList<CommentDTO> sorted_list = new ArrayList<CommentDTO>();
	
	public ArrayList<CommentDTO> sortedCommentList(ArrayList<CommentDTO> commentList) {
		
		//makeTree
		for (CommentDTO comment : commentList) {
			if (comment.getCommentID() == 0)
				continue;
			commentList.get(comment.getParentCommentID()).commentChildList.add(comment);
		}
		
		//preorder
		recursivePreorder(commentList.get(0));
		return sorted_list;
	}

	// RECURSION version O(nlogn) 최종
	public void recursivePreorder(CommentDTO comment) {

		sorted_list.add(comment);

		if (!comment.commentChildList.isEmpty()) {
			for (int i = 0; i < comment.commentChildList.size(); i++)
				recursivePreorder(comment.commentChildList.get(i));
		}
	}

}
