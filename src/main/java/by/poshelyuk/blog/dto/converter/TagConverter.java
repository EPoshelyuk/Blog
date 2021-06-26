package by.poshelyuk.blog.dto.converter;

import by.poshelyuk.blog.dto.TagDto;
import by.poshelyuk.blog.entity.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagConverter {

    public Tag convertToEntity(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setTagId(tagDto.getTagId());
        tag.setName(tagDto.getTagId());
        tag.setArticles(tagDto.getArticles());
        return tag;
    }

    public TagDto convertToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setTagId(tag.getTagId());
        tagDto.setName(tag.getTagId());
        tagDto.setArticles(tag.getArticles());
        return tagDto;
    }

}
