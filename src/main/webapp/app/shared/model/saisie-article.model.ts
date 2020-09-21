export interface ISaisieArticle {
  id?: number;
  libelle?: string;
  articleId?: number;
  contratId?: number;
}

export const defaultValue: Readonly<ISaisieArticle> = {};
