package ua.sulima.mangaservletapp.factories.service;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.factories.dao.DaoFactory;
import ua.sulima.mangaservletapp.service.CreatorService;
import ua.sulima.mangaservletapp.service.MangaService;
import ua.sulima.mangaservletapp.service.UserService;
import ua.sulima.mangaservletapp.service.impl.CreatorServiceImpl;
import ua.sulima.mangaservletapp.service.impl.MangaServiceImpl;
import ua.sulima.mangaservletapp.service.impl.UserServiceImpl;

@RequiredArgsConstructor
public class ServiceFactoryImpl extends ServiceFactory{

    @Override
    public MangaService getMangaService() {
        return new MangaServiceImpl(DaoFactory.getJdbcInstance());
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl(DaoFactory.getJdbcInstance());
    }

    @Override
    public CreatorService getCreatorService() {
        return new CreatorServiceImpl(DaoFactory.getJdbcInstance());
    }
}
