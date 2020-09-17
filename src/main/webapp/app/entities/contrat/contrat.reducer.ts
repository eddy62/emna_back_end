import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IContrat, defaultValue } from 'app/shared/model/contrat.model';

export const ACTION_TYPES = {
  FETCH_CONTRAT_LIST: 'contrat/FETCH_CONTRAT_LIST',
  FETCH_CONTRAT: 'contrat/FETCH_CONTRAT',
  CREATE_CONTRAT: 'contrat/CREATE_CONTRAT',
  UPDATE_CONTRAT: 'contrat/UPDATE_CONTRAT',
  DELETE_CONTRAT: 'contrat/DELETE_CONTRAT',
  RESET: 'contrat/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IContrat>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ContratState = Readonly<typeof initialState>;

// Reducer

export default (state: ContratState = initialState, action): ContratState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CONTRAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CONTRAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CONTRAT):
    case REQUEST(ACTION_TYPES.UPDATE_CONTRAT):
    case REQUEST(ACTION_TYPES.DELETE_CONTRAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CONTRAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CONTRAT):
    case FAILURE(ACTION_TYPES.CREATE_CONTRAT):
    case FAILURE(ACTION_TYPES.UPDATE_CONTRAT):
    case FAILURE(ACTION_TYPES.DELETE_CONTRAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONTRAT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONTRAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CONTRAT):
    case SUCCESS(ACTION_TYPES.UPDATE_CONTRAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CONTRAT):
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

const apiUrl = 'api/contrats';

// Actions

export const getEntities: ICrudGetAllAction<IContrat> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CONTRAT_LIST,
  payload: axios.get<IContrat>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IContrat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CONTRAT,
    payload: axios.get<IContrat>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IContrat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CONTRAT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IContrat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CONTRAT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IContrat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CONTRAT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
