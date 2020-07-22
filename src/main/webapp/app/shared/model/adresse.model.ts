export interface IAdresse {
  id?: number;
  numeroRue?: string;
  boitePostale?: string;
  nomRue?: string;
  codePostal?: string;
  ville?: string;
}

export const defaultValue: Readonly<IAdresse> = {};
