import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtatVariablePaie, defaultValue } from 'app/shared/model/etat-variable-paie.model';

export const ACTION_TYPES = {
  FETCH_ETATVARIABLEPAIE_LIST: 'etatVariablePaie/FETCH_ETATVARIABLEPAIE_LIST',
  FETCH_ETATVARIABLEPAIE: 'etatVariablePaie/FETCH_ETATVARIABLEPAIE',
  CREATE_ETATVARIABLEPAIE: 'etatVariablePaie/CREATE_ETATVARIABLEPAIE',
  UPDATE_ETATVARIABLEPAIE: 'etatVariablePaie/UPDATE_ETATVARIABLEPAIE',
  DELETE_ETATVARIABLEPAIE: 'etatVariablePaie/DELETE_ETATVARIABLEPAIE',
  RESET: 'etatVariablePaie/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtatVariablePaie>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtatVariablePaieState = Readonly<typeof initialState>;

// Reducer

export default (state: EtatVariablePaieState = initialState, action): EtatVariablePaieState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETATVARIABLEPAIE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETATVARIABLEPAIE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETATVARIABLEPAIE):
    case REQUEST(ACTION_TYPES.UPDATE_ETATVARIABLEPAIE):
    case REQUEST(ACTION_TYPES.DELETE_ETATVARIABLEPAIE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETATVARIABLEPAIE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETATVARIABLEPAIE):
    case FAILURE(ACTION_TYPES.CREATE_ETATVARIABLEPAIE):
    case FAILURE(ACTION_TYPES.UPDATE_ETATVARIABLEPAIE):
    case FAILURE(ACTION_TYPES.DELETE_ETATVARIABLEPAIE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATVARIABLEPAIE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATVARIABLEPAIE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETATVARIABLEPAIE):
    case SUCCESS(ACTION_TYPES.UPDATE_ETATVARIABLEPAIE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETATVARIABLEPAIE):
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

const apiUrl = 'api/etat-variable-paies';

// Actions

export const getEntities: ICrudGetAllAction<IEtatVariablePaie> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETATVARIABLEPAIE_LIST,
  payload: axios.get<IEtatVariablePaie>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtatVariablePaie> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETATVARIABLEPAIE,
    payload: axios.get<IEtatVariablePaie>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtatVariablePaie> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETATVARIABLEPAIE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtatVariablePaie> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETATVARIABLEPAIE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtatVariablePaie> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETATVARIABLEPAIE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
