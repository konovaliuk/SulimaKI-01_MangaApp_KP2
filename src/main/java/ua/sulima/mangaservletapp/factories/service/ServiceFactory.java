package ua.sulima.mangaservletapp.factories.service;

import ua.sulima.mangaservletapp.factories.dao.DaoFactory;
import ua.sulima.mangaservletapp.service.MangaService;

public abstract class ServiceFactory {
    private static volatile ServiceFactory serviceFactory;

    public static ServiceFactory getInstanceWithJdbc() {
        if (serviceFactory == null) {
            synchronized (DaoFactory.class) {
                if (serviceFactory == null) {
                    ServiceFactory service =
                            new ServiceFactoryImpl();
                    serviceFactory = service;
                }
            }
        }
        return serviceFactory;
    }

    public abstract MangaService getMangaService();
}
