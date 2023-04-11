package ua.sulima.mangaservletapp.service;

import ua.sulima.mangaservletapp.entity.Creator;

import java.util.Optional;

public interface CreatorService {
    Optional<Creator> getCreatorById(Integer id);
}
