package ua.sulima.mangaservletapp.controller.command.get;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.sulima.mangaservletapp.controller.command.Command;
import ua.sulima.mangaservletapp.entity.Creator;
import ua.sulima.mangaservletapp.entity.Manga;
import ua.sulima.mangaservletapp.service.CreatorService;
import ua.sulima.mangaservletapp.service.MangaService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor @Getter
public class MangaCatalog implements Command {
    private final MangaService mangaService;

    private final CreatorService creatorService;
    private static final String page = "manga_catalog.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing manga catalog command");
        Integer offset = 0;
        Integer limit = 10;
        String maybeOffset = request.getParameter("offset");
        if(maybeOffset != null && maybeOffset != ""){
            offset = Integer.parseInt(maybeOffset);
        }
        List<Manga> mangas = mangaService.getMangaList(limit, offset);
        request.setAttribute("mangas", mangas);

        Map<Integer, String> creatorMap = new HashMap<>();
        mangas.forEach((r)->{
            Integer creatorId = r.getArtistId();
            if(!creatorMap.containsKey(creatorId)){
                Optional<Creator> maybeCreator = creatorService.getCreatorById(creatorId);
                if(maybeCreator.isPresent()){
                    creatorMap.put(creatorId, maybeCreator.get().getName());
                }else{
                    creatorMap.put(creatorId, "errorName");
                }
            }
            creatorId = r.getAuthorId();
            if(!creatorMap.containsKey(creatorId)){
                Optional<Creator> maybeCreator = creatorService.getCreatorById(creatorId);
                if(maybeCreator.isPresent()){
                    creatorMap.put(creatorId, maybeCreator.get().getName());
                }else{
                    creatorMap.put(creatorId, "errorName");
                }
            }
        });
        request.setAttribute("creators", creatorMap);
        return page;
    }
}
