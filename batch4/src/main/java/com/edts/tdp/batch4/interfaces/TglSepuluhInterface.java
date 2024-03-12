package com.edts.tdp.batch4.interfaces;

import com.edts.tdp.batch4.bean.Tgl10.Tgl10DataDTO;
import com.edts.tdp.batch4.model.tglsepuluh.Article;
import com.edts.tdp.batch4.model.tglsepuluh.Song;

public interface TglSepuluhInterface {
    Tgl10DataDTO solveSong(Song song);
    Tgl10DataDTO solveArticle(Article article);
}
