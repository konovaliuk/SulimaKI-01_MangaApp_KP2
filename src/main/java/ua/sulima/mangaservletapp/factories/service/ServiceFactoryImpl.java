package ua.sulima.mangaservletapp.factories.service;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.factories.dao.DaoFactory;
import ua.sulima.mangaservletapp.service.MangaService;
import ua.sulima.mangaservletapp.service.impl.MangaServiceImpl;

@RequiredArgsConstructor
public class ServiceFactoryImpl extends ServiceFactory{



    @Override
    public MangaService getMangaService() {
        return new MangaServiceImpl(DaoFactory.getJdbcInstance());
    }
}
