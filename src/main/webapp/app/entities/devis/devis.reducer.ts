import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDevis, defaultValue } from 'app/shared/model/devis.model';

export const ACTION_TYPES = {
  FETCH_DEVIS_LIST: 'devis/FETCH_DEVIS_LIST',
  FETCH_DEVIS: 'devis/FETCH_DEVIS',
  CREATE_DEVIS: 'devis/CREATE_DEVIS',
  UPDATE_DEVIS: 'devis/UPDATE_DEVIS',
  DELETE_DEVIS: 'devis/DELETE_DEVIS',
  RESET: 'devis/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDevis>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DevisState = Readonly<typeof initialState>;

// Reducer

export default (state: DevisState = initialState, action): DevisState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEVIS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEVIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DEVIS):
    case REQUEST(ACTION_TYPES.UPDATE_DEVIS):
    case REQUEST(ACTION_TYPES.DELETE_DEVIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DEVIS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEVIS):
    case FAILURE(ACTION_TYPES.CREATE_DEVIS):
    case FAILURE(ACTION_TYPES.UPDATE_DEVIS):
    case FAILURE(ACTION_TYPES.DELETE_DEVIS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEVIS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEVIS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEVIS):
    case SUCCESS(ACTION_TYPES.UPDATE_DEVIS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEVIS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/devis';

// Actions

export const getEntities: ICrudGetAllAction<IDevis> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEVIS_LIST,
  payload: axios.get<IDevis>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDevis> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEVIS,
    payload: axios.get<IDevis>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDevis> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEVIS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDevis> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEVIS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDevis> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEVIS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
