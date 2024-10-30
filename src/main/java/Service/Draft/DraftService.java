package Service.Draft;

import Mapper.DraftMapper;

public class DraftService {
    private DraftMapper draftMapper;


    public String getContent() {
        return draftMapper.getContent();
    }

    public void insertDraft(String content) {
        draftMapper.insertDraft(content);
    }

    public void updateDraft(String content) {
        draftMapper.updateDraft(content);
    }
}
