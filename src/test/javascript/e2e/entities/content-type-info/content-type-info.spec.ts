/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContentTypeInfoComponentsPage, ContentTypeInfoDeleteDialog, ContentTypeInfoUpdatePage } from './content-type-info.page-object';

const expect = chai.expect;

describe('ContentTypeInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let contentTypeInfoUpdatePage: ContentTypeInfoUpdatePage;
  let contentTypeInfoComponentsPage: ContentTypeInfoComponentsPage;
  let contentTypeInfoDeleteDialog: ContentTypeInfoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ContentTypeInfos', async () => {
    await navBarPage.goToEntity('content-type-info');
    contentTypeInfoComponentsPage = new ContentTypeInfoComponentsPage();
    await browser.wait(ec.visibilityOf(contentTypeInfoComponentsPage.title), 5000);
    expect(await contentTypeInfoComponentsPage.getTitle()).to.eq('jhipsterApp.contentTypeInfo.home.title');
  });

  it('should load create ContentTypeInfo page', async () => {
    await contentTypeInfoComponentsPage.clickOnCreateButton();
    contentTypeInfoUpdatePage = new ContentTypeInfoUpdatePage();
    expect(await contentTypeInfoUpdatePage.getPageTitle()).to.eq('jhipsterApp.contentTypeInfo.home.createOrEditLabel');
    await contentTypeInfoUpdatePage.cancel();
  });

  it('should create and save ContentTypeInfos', async () => {
    const nbButtonsBeforeCreate = await contentTypeInfoComponentsPage.countDeleteButtons();

    await contentTypeInfoComponentsPage.clickOnCreateButton();
    await promise.all([
      contentTypeInfoUpdatePage.setContentTypeNameInput('contentTypeName'),
      contentTypeInfoUpdatePage.setEnviromentInput('enviroment'),
      contentTypeInfoUpdatePage.setLastModifiedByStrapiUserInput('lastModifiedByStrapiUser'),
      contentTypeInfoUpdatePage.setLastModifiedDateInput('lastModifiedDate'),
      contentTypeInfoUpdatePage.setNumberOfEntriesInput('5'),
      contentTypeInfoUpdatePage.setNumberOfParametersInput('5')
    ]);
    expect(await contentTypeInfoUpdatePage.getContentTypeNameInput()).to.eq(
      'contentTypeName',
      'Expected ContentTypeName value to be equals to contentTypeName'
    );
    expect(await contentTypeInfoUpdatePage.getEnviromentInput()).to.eq(
      'enviroment',
      'Expected Enviroment value to be equals to enviroment'
    );
    expect(await contentTypeInfoUpdatePage.getLastModifiedByStrapiUserInput()).to.eq(
      'lastModifiedByStrapiUser',
      'Expected LastModifiedByStrapiUser value to be equals to lastModifiedByStrapiUser'
    );
    expect(await contentTypeInfoUpdatePage.getLastModifiedDateInput()).to.eq(
      'lastModifiedDate',
      'Expected LastModifiedDate value to be equals to lastModifiedDate'
    );
    expect(await contentTypeInfoUpdatePage.getNumberOfEntriesInput()).to.eq('5', 'Expected numberOfEntries value to be equals to 5');
    expect(await contentTypeInfoUpdatePage.getNumberOfParametersInput()).to.eq('5', 'Expected numberOfParameters value to be equals to 5');
    await contentTypeInfoUpdatePage.save();
    expect(await contentTypeInfoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await contentTypeInfoComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ContentTypeInfo', async () => {
    const nbButtonsBeforeDelete = await contentTypeInfoComponentsPage.countDeleteButtons();
    await contentTypeInfoComponentsPage.clickOnLastDeleteButton();

    contentTypeInfoDeleteDialog = new ContentTypeInfoDeleteDialog();
    expect(await contentTypeInfoDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.contentTypeInfo.delete.question');
    await contentTypeInfoDeleteDialog.clickOnConfirmButton();

    expect(await contentTypeInfoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
