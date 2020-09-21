export interface IArticle {
  id?: number;
  titre?: string;
  intitule?: string;
  description?: string;
}

export const defaultValue: Readonly<IArticle> = {};
