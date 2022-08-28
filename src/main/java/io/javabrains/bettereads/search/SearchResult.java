package io.javabrains.bettereads.search;

import java.util.List;

import lombok.Data;

@Data
public class SearchResult {
    
    public int numFound;
    public int start;
    public boolean numFoundExact;
    public List<BookResult> docs;
    public int num_found;
    public String q;
    public Object offset;

}
