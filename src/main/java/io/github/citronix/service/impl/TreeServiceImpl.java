package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.req.TreeReqDto;
import io.github.citronix.dto.resp.TreeRespDto;
import io.github.citronix.entity.Field;
import io.github.citronix.entity.Tree;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.TreeMapper;
import io.github.citronix.repository.ITreeRepository;
import io.github.citronix.service.IFieldService;
import io.github.citronix.service.ITreeService;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TreeServiceImpl implements ITreeService {

	private final ITreeRepository repository;
	private final IFieldService fieldService;
	private final Validator validator;
	private final TreeMapper mapper;

	@Override
	public Optional<Tree> getTreeById(Long id) {
		return repository.findById(id);
	}

	@Override
	public TreeRespDto getTreeDetails(Long id) {
		Tree tree = getTreeById(id)
				.orElseThrow(() -> new CustomNotFoundException("Tree with id: " + id + " not found"));
		return mapper.entityToDto(tree);
	}

	@Override
	public List<TreeRespDto> getAllTrees(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);

		List<Tree> trees = repository.findAll(pageable).getContent();

		return trees.stream().map(t -> mapper.entityToDto(t)).collect(Collectors.toList());
	}

	@Override
	public TreeRespDto createTree(TreeReqDto dto) {
		Field field = fieldService.getFieldById(dto.getField_id())
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + dto.getField_id() + " not found"));

		ValidationMessage validationObj = validator.checkTotalTreesByFieldArea(field);

		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		validationObj = validator.checkPlantingDate(dto.getPlantingDate());

		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		Tree tree = mapper.DtoToentity(dto);
		tree.setField(field);

		Tree savedTree = repository.save(tree);
		return mapper.entityToDto(savedTree);
	}

	@Override
	public TreeRespDto updateTree(TreeReqDto dto, Long id) {
		Tree tree = getTreeById(id)
				.orElseThrow(() -> new CustomNotFoundException("Tree with id: " + id + " not found"));

		Field field = fieldService.getFieldById(dto.getField_id())
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + dto.getField_id() + " not found"));

		ValidationMessage validationObj = validator.checkTotalTreesByFieldArea(field);

		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		validationObj = validator.checkPlantingDate(dto.getPlantingDate());

		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		tree.setPlantingDate(dto.getPlantingDate());
		tree.setField(field);

		Tree updatedTree = repository.save(tree);
		return mapper.entityToDto(updatedTree);
	}

	@Override
	public void delete(Long id) {
		Tree tree = getTreeById(id)
				.orElseThrow(() -> new CustomNotFoundException("Tree with id: " + id + " not found"));
		repository.delete(tree);

	}

}
