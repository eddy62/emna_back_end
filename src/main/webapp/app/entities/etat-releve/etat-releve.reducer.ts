import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtatReleve, defaultValue } from 'app/shared/model/etat-releve.model';

export const ACTION_TYPES = {
  FETCH_ETATRELEVE_LIST: 'etatReleve/FETCH_ETATRELEVE_LIST',
  FETCH_ETATRELEVE: 'etatReleve/FETCH_ETATRELEVE',
  CREATE_ETATRELEVE: 'etatReleve/CREATE_ETATRELEVE',
  UPDATE_ETATRELEVE: 'etatReleve/UPDATE_ETATRELEVE',
  DELETE_ETATRELEVE: 'etatReleve/DELETE_ETATRELEVE',
  RESET: 'etatReleve/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtatReleve>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtatReleveState = Readonly<typeof initialState>;

// Reducer

export default (state: EtatReleveState = initialState, action): EtatReleveState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETATRELEVE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETATRELEVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETATRELEVE):
    case REQUEST(ACTION_TYPES.UPDATE_ETATRELEVE):
    case REQUEST(ACTION_TYPES.DELETE_ETATRELEVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETATRELEVE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETATRELEVE):
    case FAILURE(ACTION_TYPES.CREATE_ETATRELEVE):
    case FAILURE(ACTION_TYPES.UPDATE_ETATRELEVE):
    case FAILURE(ACTION_TYPES.DELETE_ETATRELEVE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATRELEVE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATRELEVE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETATRELEVE):
    case SUCCESS(ACTION_TYPES.UPDATE_ETATRELEVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETATRELEVE):
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

const apiUrl = 'api/etat-releves';

// Actions

export const getEntities: ICrudGetAllAction<IEtatReleve> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETATRELEVE_LIST,
  payload: axios.get<IEtatReleve>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtatReleve> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETATRELEVE,
    payload: axios.get<IEtatReleve>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtatReleve> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETATRELEVE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtatReleve> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETATRELEVE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtatReleve> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETATRELEVE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
