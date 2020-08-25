import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITypeContrat, defaultValue } from 'app/shared/model/type-contrat.model';

export const ACTION_TYPES = {
  FETCH_TYPECONTRAT_LIST: 'typeContrat/FETCH_TYPECONTRAT_LIST',
  FETCH_TYPECONTRAT: 'typeContrat/FETCH_TYPECONTRAT',
  CREATE_TYPECONTRAT: 'typeContrat/CREATE_TYPECONTRAT',
  UPDATE_TYPECONTRAT: 'typeContrat/UPDATE_TYPECONTRAT',
  DELETE_TYPECONTRAT: 'typeContrat/DELETE_TYPECONTRAT',
  RESET: 'typeContrat/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypeContrat>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TypeContratState = Readonly<typeof initialState>;

// Reducer

export default (state: TypeContratState = initialState, action): TypeContratState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TYPECONTRAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPECONTRAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPECONTRAT):
    case REQUEST(ACTION_TYPES.UPDATE_TYPECONTRAT):
    case REQUEST(ACTION_TYPES.DELETE_TYPECONTRAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TYPECONTRAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPECONTRAT):
    case FAILURE(ACTION_TYPES.CREATE_TYPECONTRAT):
    case FAILURE(ACTION_TYPES.UPDATE_TYPECONTRAT):
    case FAILURE(ACTION_TYPES.DELETE_TYPECONTRAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPECONTRAT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPECONTRAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPECONTRAT):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPECONTRAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPECONTRAT):
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

const apiUrl = 'api/type-contrats';

// Actions

export const getEntities: ICrudGetAllAction<ITypeContrat> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPECONTRAT_LIST,
  payload: axios.get<ITypeContrat>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITypeContrat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPECONTRAT,
    payload: axios.get<ITypeContrat>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITypeContrat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPECONTRAT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypeContrat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPECONTRAT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypeContrat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPECONTRAT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
