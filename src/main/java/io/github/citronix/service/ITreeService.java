package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.TreeDto;
import io.github.citronix.entity.Tree;

public interface ITreeService {
	Optional<Tree> getTreeById(Long id);

	TreeDto getTreeDetails(Long id);

	List<TreeDto> getAllTrees(Integer page);

	TreeDto createTree(TreeDto dto);

	TreeDto updateTree(TreeDto dto, Long id);

	void delete(Long id);
}
