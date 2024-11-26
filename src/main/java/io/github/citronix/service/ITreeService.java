package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.req.TreeReqDto;
import io.github.citronix.dto.resp.TreeRespDto;
import io.github.citronix.entity.Tree;

public interface ITreeService {
	Optional<Tree> getTreeById(Long id);

	TreeRespDto getTreeDetails(Long id);

	List<TreeRespDto> getAllTrees(Integer page);

	TreeRespDto createTree(TreeReqDto dto);

	TreeRespDto updateTree(TreeReqDto dto, Long id);

	void delete(Long id);
}
