import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISaisieArticle, defaultValue } from 'app/shared/model/saisie-article.model';

export const ACTION_TYPES = {
  FETCH_SAISIEARTICLE_LIST: 'saisieArticle/FETCH_SAISIEARTICLE_LIST',
  FETCH_SAISIEARTICLE: 'saisieArticle/FETCH_SAISIEARTICLE',
  CREATE_SAISIEARTICLE: 'saisieArticle/CREATE_SAISIEARTICLE',
  UPDATE_SAISIEARTICLE: 'saisieArticle/UPDATE_SAISIEARTICLE',
  DELETE_SAISIEARTICLE: 'saisieArticle/DELETE_SAISIEARTICLE',
  RESET: 'saisieArticle/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISaisieArticle>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SaisieArticleState = Readonly<typeof initialState>;

// Reducer

export default (state: SaisieArticleState = initialState, action): SaisieArticleState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SAISIEARTICLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SAISIEARTICLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SAISIEARTICLE):
    case REQUEST(ACTION_TYPES.UPDATE_SAISIEARTICLE):
    case REQUEST(ACTION_TYPES.DELETE_SAISIEARTICLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SAISIEARTICLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SAISIEARTICLE):
    case FAILURE(ACTION_TYPES.CREATE_SAISIEARTICLE):
    case FAILURE(ACTION_TYPES.UPDATE_SAISIEARTICLE):
    case FAILURE(ACTION_TYPES.DELETE_SAISIEARTICLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAISIEARTICLE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAISIEARTICLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SAISIEARTICLE):
    case SUCCESS(ACTION_TYPES.UPDATE_SAISIEARTICLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SAISIEARTICLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/saisie-articles';

// Actions

export const getEntities: ICrudGetAllAction<ISaisieArticle> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SAISIEARTICLE_LIST,
  payload: axios.get<ISaisieArticle>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISaisieArticle> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SAISIEARTICLE,
    payload: axios.get<ISaisieArticle>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISaisieArticle> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SAISIEARTICLE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISaisieArticle> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SAISIEARTICLE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISaisieArticle> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SAISIEARTICLE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
