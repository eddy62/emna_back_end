import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHeuresSupplementaires, defaultValue } from 'app/shared/model/heures-supplementaires.model';

export const ACTION_TYPES = {
  FETCH_HEURESSUPPLEMENTAIRES_LIST: 'heuresSupplementaires/FETCH_HEURESSUPPLEMENTAIRES_LIST',
  FETCH_HEURESSUPPLEMENTAIRES: 'heuresSupplementaires/FETCH_HEURESSUPPLEMENTAIRES',
  CREATE_HEURESSUPPLEMENTAIRES: 'heuresSupplementaires/CREATE_HEURESSUPPLEMENTAIRES',
  UPDATE_HEURESSUPPLEMENTAIRES: 'heuresSupplementaires/UPDATE_HEURESSUPPLEMENTAIRES',
  DELETE_HEURESSUPPLEMENTAIRES: 'heuresSupplementaires/DELETE_HEURESSUPPLEMENTAIRES',
  RESET: 'heuresSupplementaires/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHeuresSupplementaires>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type HeuresSupplementairesState = Readonly<typeof initialState>;

// Reducer

export default (state: HeuresSupplementairesState = initialState, action): HeuresSupplementairesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_HEURESSUPPLEMENTAIRES):
    case REQUEST(ACTION_TYPES.UPDATE_HEURESSUPPLEMENTAIRES):
    case REQUEST(ACTION_TYPES.DELETE_HEURESSUPPLEMENTAIRES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES):
    case FAILURE(ACTION_TYPES.CREATE_HEURESSUPPLEMENTAIRES):
    case FAILURE(ACTION_TYPES.UPDATE_HEURESSUPPLEMENTAIRES):
    case FAILURE(ACTION_TYPES.DELETE_HEURESSUPPLEMENTAIRES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_HEURESSUPPLEMENTAIRES):
    case SUCCESS(ACTION_TYPES.UPDATE_HEURESSUPPLEMENTAIRES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_HEURESSUPPLEMENTAIRES):
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

const apiUrl = 'api/heures-supplementaires';

// Actions

export const getEntities: ICrudGetAllAction<IHeuresSupplementaires> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES_LIST,
  payload: axios.get<IHeuresSupplementaires>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IHeuresSupplementaires> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HEURESSUPPLEMENTAIRES,
    payload: axios.get<IHeuresSupplementaires>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IHeuresSupplementaires> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HEURESSUPPLEMENTAIRES,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHeuresSupplementaires> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HEURESSUPPLEMENTAIRES,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHeuresSupplementaires> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HEURESSUPPLEMENTAIRES,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
