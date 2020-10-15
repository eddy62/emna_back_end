export interface ISaisieArticle {
  id?: number;
  libelle?: string;
  articleId?: number;
  contratId?: number;
  avenantId?: number;
}

export const defaultValue: Readonly<ISaisieArticle> = {};
