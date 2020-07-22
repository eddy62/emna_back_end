import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFichePaie, defaultValue } from 'app/shared/model/fiche-paie.model';

export const ACTION_TYPES = {
  FETCH_FICHEPAIE_LIST: 'fichePaie/FETCH_FICHEPAIE_LIST',
  FETCH_FICHEPAIE: 'fichePaie/FETCH_FICHEPAIE',
  CREATE_FICHEPAIE: 'fichePaie/CREATE_FICHEPAIE',
  UPDATE_FICHEPAIE: 'fichePaie/UPDATE_FICHEPAIE',
  DELETE_FICHEPAIE: 'fichePaie/DELETE_FICHEPAIE',
  RESET: 'fichePaie/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFichePaie>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FichePaieState = Readonly<typeof initialState>;

// Reducer

export default (state: FichePaieState = initialState, action): FichePaieState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FICHEPAIE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FICHEPAIE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FICHEPAIE):
    case REQUEST(ACTION_TYPES.UPDATE_FICHEPAIE):
    case REQUEST(ACTION_TYPES.DELETE_FICHEPAIE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FICHEPAIE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FICHEPAIE):
    case FAILURE(ACTION_TYPES.CREATE_FICHEPAIE):
    case FAILURE(ACTION_TYPES.UPDATE_FICHEPAIE):
    case FAILURE(ACTION_TYPES.DELETE_FICHEPAIE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FICHEPAIE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FICHEPAIE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FICHEPAIE):
    case SUCCESS(ACTION_TYPES.UPDATE_FICHEPAIE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FICHEPAIE):
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

const apiUrl = 'api/fiche-paies';

// Actions

export const getEntities: ICrudGetAllAction<IFichePaie> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FICHEPAIE_LIST,
  payload: axios.get<IFichePaie>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFichePaie> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FICHEPAIE,
    payload: axios.get<IFichePaie>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFichePaie> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FICHEPAIE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFichePaie> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FICHEPAIE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFichePaie> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FICHEPAIE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
